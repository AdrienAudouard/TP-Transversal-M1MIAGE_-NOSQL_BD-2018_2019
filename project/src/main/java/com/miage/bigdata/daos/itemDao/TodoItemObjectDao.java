package com.miage.bigdata.daos.itemDao;

import com.miage.bigdata.daos.dbDao.KeyValueDbDao;
import com.miage.bigdata.models.TodoItem;

public class TodoItemObjectDao extends KeyValueObjectDao<TodoItem> {
    public TodoItemObjectDao(KeyValueDbDao dbDao) {
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
    protected String getDatabaseID() {
        return "TestDB";
    }

    @Override
    protected String generateID() {
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
