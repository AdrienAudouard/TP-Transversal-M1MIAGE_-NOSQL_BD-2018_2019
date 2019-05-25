package com.miage.bigdata.controllers;

import com.miage.bigdata.daos.dbDao.ModelDbDao;
import com.miage.bigdata.models.Item;

import java.util.HashMap;

public abstract class ModelDbController<T extends ModelDbDao> {
    protected T dbDao;
    protected HashMap<Class<? extends Item>, ItemController> itemControllers;

    public ModelDbController() {
        this.itemControllers = new HashMap<>();
    }

    public ItemController getItemController(Class<? extends Item> cls) {
        return itemControllers.get(cls);
    }
}
