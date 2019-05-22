package com.miage.bigdata.controllers;

import com.miage.bigdata.daos.itemDao.TodoDao;
import com.miage.bigdata.daos.itemDao.TodoDaoFactory;
import com.miage.bigdata.models.TodoItem;
import lombok.NonNull;

import java.util.List;

public class TodoItemController {
    public static TodoItemController getInstance() {
        if (todoItemController == null) {
            todoItemController = new TodoItemController(TodoDaoFactory.getDao());
        }
        return todoItemController;
    }

    private static TodoItemController todoItemController;

    private final TodoDao todoDao;

    private TodoItemController(TodoDao todoDao) {
        this.todoDao = todoDao;
    }

    public TodoItem createTodoItem(@NonNull String name,
                                   @NonNull String category, boolean isComplete) {
        TodoItem todoItem = new TodoItem(name, category, isComplete);
        return todoDao.createItem(todoItem);
    }

    public boolean deleteTodoItem(@NonNull String id) {
        return todoDao.deleteItem(id);
    }

    public TodoItem getTodoItemById(@NonNull String id) {
        return todoDao.readItem(id);
    }

    public List<TodoItem> getTodoItems() {
        return todoDao.readItems();
    }

    public TodoItem updateTodoItem(@NonNull TodoItem todoItem) {

        return todoDao.updateItem(todoItem);
    }
}
