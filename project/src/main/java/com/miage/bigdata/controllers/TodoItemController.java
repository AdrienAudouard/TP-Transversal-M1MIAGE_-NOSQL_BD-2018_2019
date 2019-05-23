package com.miage.bigdata.controllers;

import com.miage.bigdata.daos.itemDao.ItemDao;
import com.miage.bigdata.daos.itemDao.TodoItemDaoFactory;
import com.miage.bigdata.models.TodoItem;
import lombok.NonNull;

import java.util.List;

public class TodoItemController {
    public static TodoItemController getInstance() {
        if (todoItemController == null) {
            todoItemController = new TodoItemController(TodoItemDaoFactory.getDao());
        }
        return todoItemController;
    }

    private static TodoItemController todoItemController;

    private final ItemDao todoDao;

    private TodoItemController(ItemDao todoDao) {
        this.todoDao = todoDao;
    }

    public TodoItem createTodoItem(@NonNull String name,
                                   @NonNull String category, boolean isComplete) {
        TodoItem todoItem = new TodoItem(name, category, isComplete);
        return todoDao.create(todoItem);
    }

    public boolean deleteTodoItem(@NonNull String id) {
        return todoDao.delete(id);
    }

    public TodoItem getTodoItemById(@NonNull String id) {
        return todoDao.getByID(id);
    }

    public List<TodoItem> getTodoItems() {
        return todoDao.readItems();
    }

    public TodoItem updateTodoItem(@NonNull TodoItem todoItem) {
        return todoDao.update(todoItem);
    }
}
