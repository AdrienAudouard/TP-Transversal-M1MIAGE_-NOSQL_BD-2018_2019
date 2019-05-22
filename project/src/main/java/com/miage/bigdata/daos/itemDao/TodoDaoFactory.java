package com.miage.bigdata.daos.itemDao;

import com.miage.bigdata.daos.dbDao.KeyValueDbDao;

public class TodoDaoFactory {
    private static TodoDao myTodoDao;

    public static TodoDao getDao() {
        if (myTodoDao == null) {
            myTodoDao = new KeyValueDbDao();
        }
        return myTodoDao;
    }
}
