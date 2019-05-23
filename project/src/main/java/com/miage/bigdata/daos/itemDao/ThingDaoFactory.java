package com.miage.bigdata.daos.itemDao;

import com.miage.bigdata.daos.dbDao.DocumentDbDao;

public class ThingDaoFactory {

    private static ItemDao myDao;

    public static ItemDao getDao() {
        if (myDao == null) {
            myDao = new DocumentDbDao("items", "TestDB");
        }
        return myDao;
    }
}
