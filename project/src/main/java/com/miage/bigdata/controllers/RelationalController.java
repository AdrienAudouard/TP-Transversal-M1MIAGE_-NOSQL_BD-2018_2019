package com.miage.bigdata.controllers;

import com.miage.bigdata.daos.dbDao.relational.RelationalModelDbDao;
import com.miage.bigdata.daos.itemDao.relational.TodoItemObjectDao;
import com.miage.bigdata.models.relational.TodoItem;

public class RelationalController extends ModelDbController<RelationalModelDbDao> {
    public RelationalController() {
        super();

        this.dbDao = new RelationalModelDbDao();
        this.itemControllers.put(TodoItem.class, new ItemController<TodoItem>(new TodoItemObjectDao(dbDao)));
    }
}
