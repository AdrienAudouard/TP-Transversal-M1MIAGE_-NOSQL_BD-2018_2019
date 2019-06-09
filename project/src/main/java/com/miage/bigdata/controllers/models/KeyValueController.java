package com.miage.bigdata.controllers.models;

import com.miage.bigdata.controllers.item.ItemController;
import com.miage.bigdata.daos.dbDao.keyvalue.KeyValueModelDbDao;
import com.miage.bigdata.daos.itemDao.keyvalue.FeedbackObjectDao;
import com.miage.bigdata.models.keyvalue.FeedbackItem;

class KeyValueController extends ModelDbController<KeyValueModelDbDao> {
    KeyValueController() {
        super();

        this.dbDao = new KeyValueModelDbDao();
        this.itemControllers.put(FeedbackItem.class, new ItemController<FeedbackItem>(new FeedbackObjectDao(dbDao)));
    }
}
