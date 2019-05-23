package com.miage.bigdata.daos.dbDao;

import com.google.gson.Gson;
import com.miage.bigdata.daos.itemDao.ItemDao;
import com.miage.bigdata.models.Item;
import lombok.NonNull;

import java.util.List;

public abstract class ModelDbDao implements ItemDao {

    // The name of our collection.
    protected String collectionId;

    // The id of our collection.
    protected String databaseId;

    // We'll use Gson for POJO <=> JSON serialization for this example.
    protected static Gson gson = new Gson();

    public abstract String getDatabaseID();

    public abstract List<Item> readAll();

    public abstract Item create(Item item);

    public abstract Item getByID(@NonNull String id);

    public abstract boolean delete(@NonNull String id);

    public abstract Item update(@NonNull Item item);

    public abstract String generateID();

    public abstract boolean createTable();

    public abstract boolean populateTable();

    public abstract boolean deleteTable();

    public void reinitializeTable() {
        deleteTable();
        createTable();
        populateTable();
    }
}
