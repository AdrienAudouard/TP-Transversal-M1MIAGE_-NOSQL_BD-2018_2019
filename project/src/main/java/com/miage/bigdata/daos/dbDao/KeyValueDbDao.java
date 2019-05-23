package com.miage.bigdata.daos.dbDao;

import com.miage.bigdata.daos.clientsDao.KeyValueClientDao;
import com.miage.bigdata.models.Item;
import com.microsoft.azure.documentdb.*;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public class KeyValueDbDao<T> extends ModelDbDao {

    private DocumentClient client;

    private Database databaseCache;

    private DocumentCollection collectionCache;

    private Class<T> tClass;

    public KeyValueDbDao(String collectionId, String databaseId, Class<T> tClass) {
        this.collectionId = collectionId; //"items";
        this.databaseId = databaseId; //"TestDB";
        this.tClass = tClass;
        client = new KeyValueClientDao().getClient();
    }

    @Override
    public String getDatabaseID() {
        return null;
    }

    @Override
    public List<T> readAll() {
        List<T> items = new ArrayList<>();

        List<Document> documentList = client
                .queryDocuments(getCollection().getSelfLink(),
                        "SELECT * FROM root r WHERE r.entityType = 'todoItem'",
                        null).getQueryIterable().toList();

        for (Document item : documentList) {
            items.add(gson.fromJson(item.toString(), tClass));
        }

        return items;
    }

    @Override
    public T create(Object item) {
        Document itemDocument = new Document(gson.toJson(item));

        itemDocument.set("entityType", "todoItem");

        try {
            itemDocument = client.createDocument(
                    getCollection().getSelfLink(), itemDocument, null,
                    false).getResource();
        } catch (DocumentClientException e) {
            e.printStackTrace();
            return null;
        }

        return gson.fromJson(itemDocument.toString(), tClass);
    }

    @Override
    public T getByID(@NonNull String id) {
        Document todoItemDocument = getDocumentById(id);

        if (todoItemDocument != null) {
            return gson.fromJson(todoItemDocument.toString(), tClass);
        } else {
            return null;
        }
    }

    @Override
    public boolean delete(@NonNull String id) {
        Document todoItemDocument = getDocumentById(id);

        try {
            // Delete the document by self link.
            client.deleteDocument(todoItemDocument.getSelfLink(), null);
        } catch (DocumentClientException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public T update(@NonNull Object item) {
        Item itemCast = (Item) item;
        Document itemDocument = getDocumentById(itemCast.getId());

        itemDocument.set("name", itemCast.getName());

        try {
            // Persist/replace the updated document.
            itemDocument = client.replaceDocument(itemDocument,
                    null).getResource();
        } catch (DocumentClientException e) {
            e.printStackTrace();
            return null;
        }

        return gson.fromJson(itemDocument.toString(), tClass);
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

    private Document getDocumentById(String id) {
        List<Document> documentList = client
                .queryDocuments(getCollection().getSelfLink(),
                        "SELECT * FROM root r WHERE r.id='" + id + "'", null)
                .getQueryIterable().toList();

        if (documentList.size() > 0) {
            return documentList.get(0);
        } else {
            return null;
        }
    }

    private DocumentCollection getCollection() {
        if (collectionCache == null) {
            // Get the collection if it exists.
            List<DocumentCollection> collectionList = client
                    .queryCollections(
                            getDatabase().getSelfLink(),
                            "SELECT * FROM root r WHERE r.id='" + collectionId
                                    + "'", null).getQueryIterable().toList();

            if (collectionList.size() > 0) {
                // Cache the collection object so we won't have to query for it
                // later to retrieve the selfLink.
                collectionCache = collectionList.get(0);
            } else {
                // Create the collection if it doesn't exist.
                try {
                    DocumentCollection collectionDefinition = new DocumentCollection();
                    collectionDefinition.setId(collectionId);

                    collectionCache = client.createCollection(
                            getDatabase().getSelfLink(),
                            collectionDefinition, null).getResource();
                } catch (DocumentClientException e) {
                    // TODO: Something has gone terribly wrong - the app wasn't
                    // able to query or create the collection.
                    // Verify your connection, endpoint, and key.
                    e.printStackTrace();
                }
            }
        }

        return collectionCache;
    }

    private Database getDatabase() {
        if (databaseCache == null) {
            // Get the database if it exists
            List<Database> databaseList = client
                    .queryDatabases(
                            "SELECT * FROM root r WHERE r.id='" + databaseId
                                    + "'", null).getQueryIterable().toList();

            if (databaseList.size() > 0) {
                // Cache the database object so we won't have to query for it
                // later to retrieve the selfLink.
                databaseCache = databaseList.get(0);
            } else {
                // Create the database if it doesn't exist.
                try {
                    Database databaseDefinition = new Database();
                    databaseDefinition.setId(databaseId);

                    databaseCache = client.createDatabase(
                            databaseDefinition, null).getResource();
                } catch (DocumentClientException e) {
                    // TODO: Something has gone terribly wrong - the app wasn't
                    // able to query or create the collection.
                    // Verify your connection, endpoint, and key.
                    e.printStackTrace();
                }
            }
        }

        return databaseCache;
    }
}
