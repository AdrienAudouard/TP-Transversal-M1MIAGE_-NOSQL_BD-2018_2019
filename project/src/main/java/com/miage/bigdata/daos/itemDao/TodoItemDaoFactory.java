package com.miage.bigdata.daos.itemDao;

import com.miage.bigdata.daos.dbDao.KeyValueDbDao;

public class TodoItemDaoFactory {

    private static ItemDao myDao;

    public static ItemDao getDao() {
        if (myDao == null) {
            myDao = new KeyValueDbDao("items", "TestDB");
        }
        return myDao;
    }
}
