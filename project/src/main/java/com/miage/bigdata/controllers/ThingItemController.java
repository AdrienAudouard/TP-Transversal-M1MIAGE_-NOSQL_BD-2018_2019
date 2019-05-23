package com.miage.bigdata.controllers;

import com.miage.bigdata.daos.dbDao.document.DocumentDbDao;
import com.miage.bigdata.daos.itemDao.document.ThingObjectDao;

public class ThingItemController {
    private DocumentDbDao documentDbDao;
    private ThingObjectDao thingObjectDao;


    public ThingItemController() {
        documentDbDao = new DocumentDbDao();
        thingObjectDao = new ThingObjectDao(documentDbDao);
    }

    public ThingObjectDao getThingObjectDao() {
        return thingObjectDao;
    }
}
