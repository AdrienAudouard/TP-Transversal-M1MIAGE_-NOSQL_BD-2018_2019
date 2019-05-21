package com.miage.bigdata.controller;

import java.util.List;

import com.miage.bigdata.dao.TodoItemDAO;
import com.miage.bigdata.entity.TodoItem;
import lombok.NonNull;

public class TodoItemController {

    private TodoItemDAO dao = new TodoItemDAO();

    public TodoItem createTodoItem(@NonNull TodoItem todoItem) {
        return dao.createTodoItem(todoItem);
    }

    public boolean deleteTodoItem(@NonNull String id) {
        return dao.deleteTodoItem(id);
    }

    public TodoItem getTodoItemById(@NonNull String id) {
        return dao.readTodoItem(id);
    }

    public List<TodoItem> getTodoItems() {
        return dao.readTodoItems();
    }

    public TodoItem updateTodoItem(@NonNull String id, boolean isComplete) {
        return dao.updateTodoItem(id, isComplete);
    }

    public TodoItem updateTodoItem(@NonNull TodoItem todoItem) {
        return dao.updateTodoItem(todoItem);
    }
}
