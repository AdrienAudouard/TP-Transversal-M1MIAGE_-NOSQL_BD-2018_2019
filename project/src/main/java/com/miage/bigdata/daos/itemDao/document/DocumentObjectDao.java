package com.miage.bigdata.daos.itemDao.document;

import com.miage.bigdata.daos.dbDao.document.DocumentDbDao;
import com.miage.bigdata.daos.itemDao.ObjectDao;
import com.miage.bigdata.models.document.DocumentItem;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import lombok.NonNull;
import org.bson.Document;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public abstract class DocumentObjectDao<T extends DocumentItem> extends ObjectDao<T, DocumentDbDao> {

    protected MongoClient mongoClient;

    protected int lastNextID;

    public DocumentObjectDao(DocumentDbDao dbDao) {
        super(dbDao);

        this.mongoClient = dbDao.connect();
    }

    @Override
    public T create(@NonNull T item) {
        MongoCollection collection = getCollection();

        collection.insertOne(item.toDocument());

        return item;
    }

    @Override
    public List<T> readAll() {
        MongoCollection collection = getCollection();

        ArrayList<T> list = new ArrayList<>();

        for (Object o : collection.find()) {
            Document document = (Document) o;
            list.add(instanciateItemFromDocument(document));
        }

        return list;
    }

    @Override
    public boolean delete(@NonNull String id) {
        MongoCollection collection = getCollection();

        DeleteResult deleteResult = collection.deleteMany(eq("id", id));

        return deleteResult.getDeletedCount() != 0;
    }

    @Override
    public T getByID(@NonNull String id) {
        MongoCollection collection = getCollection();

        Object o = collection.find(eq("id", id)).first();

        if (o == null) {
            return null;
        }

        Document document = (Document) o;

        return instanciateItemFromDocument(document);
    }

    @Override
    public T update(@NonNull T item) {
        MongoCollection collection = getCollection();

        UpdateResult updateResult = collection.replaceOne(eq("id", item.getId()), item.toDocument());

        if (updateResult.getModifiedCount() != 0) {
            return item;
        }

        return null;
    }

    @Override
    public String generateID() {
        List<T> list = this.readAll();

        int newID = 1;

        if (list.size() != 0) {
            list.sort(Comparator.comparingInt(o -> Integer.parseInt(o.getId())));

            newID = Integer.parseInt(list.get(list.size()-1).getId()) + 1;
        }

        if (newID <= lastNextID) {
            newID= lastNextID + 1;
        }

        lastNextID = newID;

        return Integer.toString(newID);
    }

    public abstract String getCollectionName();

    protected T instanciateItemFromDocument(Document document) {
        try {
            return getItemClass().getConstructor(Document.class).newInstance(document);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected String getDatabaseID() {
        return "TestDoc";
    }

    public MongoDatabase getDatabase() {
        return this.mongoClient.getDatabase(getDatabaseID());
    }

    public MongoCollection<Document> getCollection() {
        return getDatabase().getCollection(getCollectionName());
    }
}
