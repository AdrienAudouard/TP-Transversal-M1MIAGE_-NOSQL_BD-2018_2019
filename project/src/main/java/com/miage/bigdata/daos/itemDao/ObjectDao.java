package com.miage.bigdata.daos.itemDao;

import com.google.gson.Gson;
import com.miage.bigdata.daos.dbDao.ModelDbDao;
import com.miage.bigdata.models.Item;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public abstract class ObjectDao<T extends Item, U extends ModelDbDao> {
    // We'll use Gson for POJO <=> JSON serialization for this example.
    protected static Gson gson = new Gson();

    protected U dbDao;

    public ObjectDao(U dbDao) {
        this.dbDao = dbDao;
    }

    /**
     * @return The id of the database
     */
    protected abstract String getDatabaseID();

    /**
     * @return List of item
     */
    public abstract List<T> readAll();

    /**
     * @return Item created
     */
    public abstract T create(@NonNull T item);

    /**
     * Create all items
     * @param items
     * @return Items created
     */
    public List<T> create(@NonNull T ...items) {
        ArrayList<T> arrayList = new ArrayList<>();

        for (T item : items) {
            arrayList.add(create(item));
        }

        return arrayList;
    }

    /**
     * @param id
     * @return Item width id
     */
    public abstract T getByID(@NonNull String id);

    /**
     * Delete an item
     * @param id id of the item to delete
     * @return True if deleted else no
     */
    public abstract boolean delete(@NonNull String id);


    public boolean delete(@NonNull String ...ids) {
        for (String id : ids) {
            if (!delete(id)) {
                return false;
            }
        }

        return true;
    }

    /**
     * @param item Item to update
     * @return Item updated
     */
    public abstract T update(@NonNull T item);

    /**
     * @return Generate a new ID for the item
     */
    public abstract String generateID();

    /**
     * Create the table or the equivalent in the DB
     * @return true if success
     */
    public abstract boolean createTable();

    /**
     * Add all elements in the Database
     * @return true if success
     */
    public abstract boolean populateTable();

    /**
     * Delete the table and all of his elements
     * @return
     */
    public abstract boolean deleteTable();

    /**
     * Get the class of the item
     * @return
     */
    protected abstract Class<T> getItemClass();

    /**
     * Delete, create and populate the table
     */
    public void reinitializeTable() {
        deleteTable();
        createTable();
        populateTable();
    }
}
