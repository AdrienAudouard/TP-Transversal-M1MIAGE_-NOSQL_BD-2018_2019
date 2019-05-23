package com.miage.bigdata.daos.itemDao;

import com.miage.bigdata.daos.dbDao.DocumentDbDao;
import com.miage.bigdata.models.TodoItem;

public class TodoItemObjectDao extends DocumentObjectDao<TodoItem> {
    public TodoItemObjectDao(DocumentDbDao dbDao) {
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
