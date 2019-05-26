package com.miage.bigdata.controllers;

import com.miage.bigdata.daos.dbDao.document.DocumentModelDbDao;
import com.miage.bigdata.daos.dbDao.keyvalue.KeyValueModelDbDao;
import com.miage.bigdata.daos.itemDao.document.OrderObjectDao;
import com.miage.bigdata.daos.itemDao.document.ProductObjectDao;
import com.miage.bigdata.daos.itemDao.document.ThingObjectDao;
import com.miage.bigdata.daos.itemDao.keyvalue.FeedbackObjectDao;
import com.miage.bigdata.models.document.OrderItem;
import com.miage.bigdata.models.document.ProductItem;
import com.miage.bigdata.models.document.ThingItem;

public class KeyValueController extends ModelDbController<KeyValueModelDbDao> {
    public KeyValueController() {
        super();

        this.dbDao = new KeyValueModelDbDao();
        this.itemControllers.put(ThingItem.class, new ItemController<ThingItem>(new FeedbackObjectDao(dbDao)));
    }
}
