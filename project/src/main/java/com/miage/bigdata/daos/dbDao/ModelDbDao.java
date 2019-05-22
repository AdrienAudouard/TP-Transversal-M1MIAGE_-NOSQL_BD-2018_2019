package com.miage.bigdata.daos.dbDao;

import com.google.gson.Gson;
import com.microsoft.azure.documentdb.*;

import java.util.List;

public abstract class ModelDbDao<T> {

    // The name of our collection.
    protected static String collectionId;

    // The id of our collection.
    protected static String databaseId;

    protected Gson gson = new Gson();

    private static Database databaseCache;

    private static DocumentCollection collectionCache;

    public abstract T createItem(T item);

    public abstract T readItem(String id);

    public abstract List<T> readItems();

    public abstract T updateItem(T item);

    public abstract boolean deleteItem(String id);
}
