package com.miage.bigdata.controllers;

import com.miage.bigdata.daos.dbDao.keyValue.KeyValueModelDbDao;
import com.miage.bigdata.daos.itemDao.keyValue.TodoItemObjectDao;
import com.miage.bigdata.models.keyValue.TodoItem;

public class KeyValueController extends ModelDbController<KeyValueModelDbDao> {
    public KeyValueController() {
        super();

        this.dbDao = new KeyValueModelDbDao();
        this.itemControllers.put(TodoItem.class, new ItemController<TodoItem>(new TodoItemObjectDao(dbDao)));
    }
}
