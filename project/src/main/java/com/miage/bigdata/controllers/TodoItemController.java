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

    TodoItemController(TodoDao todoDao) {
        this.todoDao = todoDao;
    }

    public TodoItem createTodoItem(@NonNull String name,
                                   @NonNull String category, boolean isComplete) {
        TodoItem todoItem = TodoItem.builder().name(name).category(category)
                .complete(isComplete).build();
        return todoDao.createTodoItem(todoItem);
    }

    public boolean deleteTodoItem(@NonNull String id) {
        return todoDao.deleteTodoItem(id);
    }

    public TodoItem getTodoItemById(@NonNull String id) {
        return todoDao.readTodoItem(id);
    }

    public List<TodoItem> getTodoItems() {
        return todoDao.readTodoItems();
    }

    public TodoItem updateTodoItem(@NonNull String id, boolean isComplete) {
        return todoDao.updateTodoItem(id, isComplete);
    }
}
