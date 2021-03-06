package com.miage.bigdata.daos.itemDao.relational;

import com.miage.bigdata.daos.dbDao.relational.RelationalModelDbDao;
import com.miage.bigdata.daos.itemDao.ObjectDao;
import com.miage.bigdata.models.relational.RelationalItem;
import com.microsoft.azure.documentdb.*;

import java.util.ArrayList;
import java.util.List;

public abstract class RelationalObjectDao<T extends RelationalItem> extends ObjectDao<T, RelationalModelDbDao> {
    // The DocumentDB Client
    protected static DocumentClient documentClient;

    // Cache for the database object, so we don't have to query for it to
    // retrieve self links.
    protected static Database databaseCache;

    // Cache for the collection object, so we don't have to query for it to
    // retrieve self links.
    protected static DocumentCollection collectionCache;

    protected abstract String getCollectionId();

    protected abstract String getEntityName();

    public RelationalObjectDao(RelationalModelDbDao dbDao) {
        super(dbDao);

        documentClient = dbDao.connect();
    }

    @Override
    public List<T> readAll() {
        List<T> todoItems = new ArrayList<>();

        // Retrieve the TodoItem documents
        List<Document> documentList = documentClient
                .queryDocuments(getCollection().getSelfLink(),
                        "SELECT * FROM root r WHERE r.entityType = '"+ getEntityName()  +"'",
                        null).getQueryIterable().toList();

        // De-serialize the documents in to TodoItems.
        for (Document document : documentList) {

            T item = instanciateItemFromJSON(document.toString());
            todoItems.add(item);
        }

        return todoItems;
    }

    @Override
    public T getByID(String id) {
        // Retrieve the document by id using our helper method.
        Document document = getDocumentById(id);

        if (document != null) {
            // De-serialize the document in to a TodoItem.
            return instanciateItemFromJSON(document.toString());
        } else {
            return null;
        }
    }

    @Override
    public T create(T item) {
        // Serialize the TodoItem as a JSON Document.
        Document document = new Document(gson.toJson(item));

        // Annotate the document as a TodoItem for retrieval (so that we can
        // store multiple entity types in the collection).
        document.set("entityType", getEntityName());

        try {
            // Persist the document using the DocumentClient.
            document = documentClient.createDocument(
                    getCollection().getSelfLink(), document, null,
                    false).getResource();
        } catch (DocumentClientException e) {
            e.printStackTrace();
            return null;
        }

        return instanciateItemFromJSON(document.toString());
    }

    @Override
    public T update(T item) {
        Document document = getDocumentById(item.getItemId());

        document = item.updateDocument(document);

        try {
            // Persist/replace the updated document.
            document = documentClient.replaceDocument(document,
                    null).getResource();
        } catch (DocumentClientException e) {
            e.printStackTrace();
            return null;
        }

        return instanciateItemFromJSON(document.toString());
    }

    @Override
    public boolean delete(String id) {
        // Query for the document to retrieve the self link.
        Document todoItemDocument = getDocumentById(id);

        try {
            // Delete the document by self link.
            documentClient.deleteDocument(todoItemDocument.getSelfLink(), null);
        } catch (DocumentClientException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    protected Document getDocumentById(String id) {
        // Retrieve the document using the DocumentClient.
        List<Document> documentList = documentClient
                .queryDocuments(getCollection().getSelfLink(),
                        "SELECT * FROM root r WHERE r.itemId='" + id + "'", null)
                .getQueryIterable().toList();

        if (documentList.size() > 0) {
            return documentList.get(0);
        } else {
            return null;
        }
    }

    protected DocumentCollection getCollection() {
        if (collectionCache == null) {
            // Get the collection if it exists.
            List<DocumentCollection> collectionList = documentClient
                    .queryCollections(
                            getDatabase().getSelfLink(),
                            "SELECT * FROM root r WHERE r.id='" + getCollectionId()
                                    + "'", null).getQueryIterable().toList();

            if (collectionList.size() > 0) {

                // Cache the collection object so we won't have to query for it
                // later to retrieve the selfLink.
                collectionCache = collectionList.get(0);
            } else {
                // Create the collection if it doesn't exist.
                try {
                    DocumentCollection collectionDefinition = new DocumentCollection();
                    collectionDefinition.setId(getCollectionId());

                    collectionCache = documentClient.createCollection(
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
            List<Database> databaseList = documentClient
                    .queryDatabases(
                            "SELECT * FROM root r WHERE r.id='" + getDatabaseID()
                                    + "'", null).getQueryIterable().toList();

            if (databaseList.size() > 0) {
                // Cache the database object so we won't have to query for it
                // later to retrieve the selfLink.
                databaseCache = databaseList.get(0);
            } else {
                // Create the database if it doesn't exist.
                try {
                    Database databaseDefinition = new Database();
                    databaseDefinition.setId(getDatabaseID());

                    databaseCache = documentClient.createDatabase(
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

    @Override
    protected String getDatabaseID() {
        return "ToDoList";
    }
}
