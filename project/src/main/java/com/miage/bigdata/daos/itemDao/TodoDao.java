package com.miage.bigdata.daos.itemDao;

import com.miage.bigdata.models.TodoItem;

import java.util.List;

public interface TodoDao {
    /**
     * @return A list of TodoItems
     */
    public List<TodoItem> readItems();

    /**
     * @param todoItem
     * @return whether the todoItem was persisted.
     */
    public TodoItem createItem(TodoItem todoItem);

    /**
     * @param id
     * @return the TodoItem
     */
    public TodoItem readItem(String id);

    /**
     * @param todoItem
     * @return the TodoItem
     */
    public TodoItem updateItem(TodoItem todoItem);

    /**
     *
     * @param id
     * @return whether the delete was successful.
     */
    public boolean deleteItem(String id);
}
