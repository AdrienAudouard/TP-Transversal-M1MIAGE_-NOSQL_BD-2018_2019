package com.miage.bigdata.controllers;

import com.miage.bigdata.daos.dbDao.keyvalue.KeyValueModelDbDao;
import com.miage.bigdata.daos.itemDao.keyvalue.FeedbackObjectDao;
import com.miage.bigdata.models.keyvalue.FeedbackItem;

public class KeyValueController extends ModelDbController<KeyValueModelDbDao> {
    public KeyValueController() {
        super();

        this.dbDao = new KeyValueModelDbDao();
        this.itemControllers.put(FeedbackItem.class, new ItemController<FeedbackItem>(new FeedbackObjectDao(dbDao)));
    }
}
