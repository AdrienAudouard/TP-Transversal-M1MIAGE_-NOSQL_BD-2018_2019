package com.miage.bigdata.controllers;

import com.miage.bigdata.daos.dbDao.keyValue.KeyValueDbDao;
import com.miage.bigdata.daos.itemDao.keyValue.TodoItemObjectDao;

public class TodoItemController {
    private KeyValueDbDao keyValueDbDao;
    private TodoItemObjectDao todoItemObjectDao;

    public TodoItemController() {
        this.keyValueDbDao = new KeyValueDbDao();
        this.todoItemObjectDao = new TodoItemObjectDao(this.keyValueDbDao);
    }

    public TodoItemObjectDao getTodoItemObjectDao() {
        return todoItemObjectDao;
    }
}
