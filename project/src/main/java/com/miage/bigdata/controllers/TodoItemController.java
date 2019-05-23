package com.miage.bigdata.controllers;

import com.miage.bigdata.daos.dbDao.DocumentDbDao;
import com.miage.bigdata.daos.itemDao.TodoItemObjectDao;

public class TodoItemController {
    private DocumentDbDao documentDbDao;
    private TodoItemObjectDao todoItemObjectDao;

    public TodoItemController() {
        this.documentDbDao = new DocumentDbDao();
        this.todoItemObjectDao = new TodoItemObjectDao(this.documentDbDao);
    }

    public TodoItemObjectDao getTodoItemObjectDao() {
        return todoItemObjectDao;
    }
}
