package com.miage.bigdata.controllers;

import com.miage.bigdata.daos.itemDao.ObjectDao;
import com.miage.bigdata.models.Item;

import java.util.List;

public class ItemController<T extends Item> {
    protected final ObjectDao objectDao;

    public ItemController(ObjectDao objectDao) {
        this.objectDao = objectDao;
    }

    public List<T> readAll() {
        return objectDao.readAll();
    }

    public String generateID() {
        return objectDao.generateID();
    }

    public boolean createTable() {
        return objectDao.createTable();
    }

    public boolean deleteTable() {
        return objectDao.deleteTable();
    }

    public boolean populateTable() {
        return objectDao.populateTable();
    }

    public T create(T item) {
        return (T) objectDao.create(item);
    }

    @SafeVarargs
    public final List<T> create(T... items) {
        return objectDao.create(items);
    }

    public T getByID(String id) {
        return (T) objectDao.getByID(id);
    }

    public boolean delete(String id) {
        return objectDao.delete(id);
    }

    public boolean delete(String ... ids) {
        return objectDao.delete(ids);
    }

    public T update(T item) {
        return (T) objectDao.update(item);
    }

    public void reinitializeTable() {
        objectDao.reinitializeTable();
    }
}
