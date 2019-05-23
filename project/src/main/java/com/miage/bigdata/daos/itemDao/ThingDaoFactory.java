package com.miage.bigdata.daos.itemDao;

import com.miage.bigdata.daos.dbDao.DocumentDbDao;
import com.miage.bigdata.models.Thing;

public class ThingDaoFactory {

    private static ItemDao myDao;

    public static ItemDao getDao() {
        if (myDao == null) {
            myDao = new DocumentDbDao<>("things", "TestDoc", Thing.class);
        }
        return myDao;
    }
}
