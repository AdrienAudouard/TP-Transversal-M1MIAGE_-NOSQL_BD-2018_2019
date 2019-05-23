package com.miage.bigdata.daos.dbDao;

import com.miage.bigdata.daos.clientsDao.DocumentClientDao;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.NonNull;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class DocumentDbDao<T> extends ModelDbDao {

    private MongoClient client;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    private Class<T> tClass;

    public DocumentDbDao(String collectionId, String databaseId, Class<T> tClass) {
        this.collectionId = collectionId; //"items";
        this.databaseId = databaseId; //"TestDB";
        this.tClass = tClass;
        this.client = new DocumentClientDao().getClient();
        this.database = client.getDatabase(databaseId);
        this.collection = database.getCollection(collectionId);
    }

    @Override
    public String getDatabaseID() {
        return null;
    }

    @Override
    public List<T> readAll() {
        List<T> items = new ArrayList<>();

        List<Document> list = collection.find().into(new ArrayList<>());
        for (Document document : list) {
            System.out.println(document.toString());
            //items.add(gson.fromJson(document.toString(), tClass));
        }

        return items;
    }

    @Override
    public T create(Object item) {
        return null;
    }

    @Override
    public T getByID(@NonNull String id) {
        return null;
    }

    @Override
    public boolean delete(@NonNull String id) {
        return false;
    }

    @Override
    public T update(Object item) {
        return null;
    }

    @Override
    public String generateID() {
        return null;
    }

    @Override
    public boolean createTable() {
        return false;
    }

    @Override
    public boolean populateTable() {
        return false;
    }

    @Override
    public boolean deleteTable() {
        return false;
    }
}
