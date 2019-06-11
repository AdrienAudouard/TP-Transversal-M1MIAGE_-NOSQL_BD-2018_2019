package com.miage.bigdata.controllers.models;

import com.miage.bigdata.controllers.item.ItemController;
import com.miage.bigdata.daos.dbDao.ModelDbDao;
import com.miage.bigdata.models.Item;

import java.util.HashMap;

abstract class ModelDbController<T extends ModelDbDao> {
    protected T dbDao;
    protected final HashMap<Class<? extends Item>, ItemController> itemControllers;

    ModelDbController() {
        this.itemControllers = new HashMap<>();
    }

    ItemController getItemController(Class<? extends Item> cls) {
        return itemControllers.get(cls);
    }
}
