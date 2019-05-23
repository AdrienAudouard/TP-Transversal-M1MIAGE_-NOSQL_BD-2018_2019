package com.miage.bigdata.controllers;

import com.miage.bigdata.daos.itemDao.ItemDao;
import lombok.NonNull;

import java.util.List;

public class Controller<T> {

    private final ItemDao<T> todoDao;

    public Controller(ItemDao<T> todoDao) {
        this.todoDao = todoDao;
    }

    public T create(@NonNull T item) {
        return todoDao.create(item);
    }

    public boolean delete(@NonNull String id) {
        return todoDao.delete(id);
    }

    public T getByID(String id) {
        return todoDao.getByID(id);
    }

    public List readAll() {
        return todoDao.readAll();
    }

    public T update(T item) {
        return todoDao.update(item);
    }
}
