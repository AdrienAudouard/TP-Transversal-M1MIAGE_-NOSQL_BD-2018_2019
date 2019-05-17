package com.miage.bigdata.repository;


import com.miage.bigdata.entity.TodoItem;

import java.util.List;

public interface Repository {
    /**
     * @return A list of TodoItems
     */
    public List<TodoItem> readTodoItems();

    /**
     * @param todoItem
     * @return whether the todoItem was persisted.
     */
    public TodoItem createTodoItem(TodoItem todoItem);

    /**
     * @param id
     * @return the TodoItem
     */
    public TodoItem readTodoItem(String id);

    /**
     * @param id
     * @return the TodoItem
     */
    public TodoItem updateTodoItem(String id, boolean isComplete);

    /**
     *
     * @param id
     * @return whether the delete was successful.
     */
    public boolean deleteTodoItem(String id);
}