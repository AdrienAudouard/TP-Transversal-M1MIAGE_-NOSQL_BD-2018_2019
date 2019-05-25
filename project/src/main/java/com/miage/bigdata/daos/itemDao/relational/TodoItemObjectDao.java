package com.miage.bigdata.daos.itemDao.relational;

import com.miage.bigdata.daos.dbDao.relational.RelationalModelDbDao;
import com.miage.bigdata.models.relational.TodoItem;

public class TodoItemObjectDao extends RelationalObjectDao<TodoItem> {
    public TodoItemObjectDao(RelationalModelDbDao dbDao) {
        super(dbDao);
    }

    @Override
    protected String getCollectionId() {
        return "TestCollection";
    }

    @Override
    protected String getEntityName() {
        return "todoItem";
    }

    @Override
    public String generateID() {
        return null;
    }

    @Override
    public boolean createTable() {
        return false;
    }

    @Override
    public boolean populateTable() {
        return false;
    }

    @Override
    public boolean deleteTable() {
        return false;
    }

    @Override
    protected Class<TodoItem> getItemClass() {
        return TodoItem.class;
    }
}
