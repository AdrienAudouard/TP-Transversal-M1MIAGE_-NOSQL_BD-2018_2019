package com.miage.bigdata.controllers.models;

import com.miage.bigdata.controllers.item.ItemController;
import com.miage.bigdata.daos.dbDao.relational.RelationalModelDbDao;
import com.miage.bigdata.daos.itemDao.relational.TodoItemObjectDao;
import com.miage.bigdata.models.relational.TodoItem;

class RelationalController extends ModelDbController<RelationalModelDbDao> {
    RelationalController() {
        super();

        this.dbDao = new RelationalModelDbDao();
        this.itemControllers.put(TodoItem.class, new ItemController<TodoItem>(new TodoItemObjectDao(dbDao)));
    }
}
