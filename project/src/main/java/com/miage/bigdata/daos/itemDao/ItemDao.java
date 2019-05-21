package com.miage.bigdata.daos.itemDao;

import com.google.gson.Gson;
import lombok.NonNull;

import java.util.List;

public abstract class ItemDao<T> {
    // We'll use Gson for POJO <=> JSON serialization for this example.
    protected static Gson gson = new Gson();

    /**
     * @return The id of the database
     */
    abstract String getDatabaseID();

    /**
     * @return List of item
     */
    abstract List<T> readAll();

    /**
     * @return Item created
     */
    abstract T create();

    /**
     * @param id
     * @return Item width id
     */
    abstract T getByID(@NonNull String id);

    /**
     * Delete an item
     * @param item Item to delete
     * @return True if deleted else no
     */
    abstract boolean delete(@NonNull T item);

    /**
     * @param item Item to update
     * @return Item updated
     */
    abstract T update(@NonNull T item);

    /**
     * @return Generate a new ID for the item
     */
    abstract String generateID();

    /**
     * Create the table or the equivalent in the DB
     * @return true if success
     */
    abstract boolean createTable();

    /**
     * Add all elements in the Database
     * @return true if success
     */
    abstract boolean populateTable();

    /**
     * Delete the table and all of his elements
     * @return
     */
    abstract boolean deleteTable();

    /**
     * Delete, create and populate the table
     */
    void reinitializeTable() {
        deleteTable();
        createTable();
        populateTable();
    }
}
