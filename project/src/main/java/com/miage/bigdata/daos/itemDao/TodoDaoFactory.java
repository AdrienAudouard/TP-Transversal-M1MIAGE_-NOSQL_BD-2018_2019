package com.miage.bigdata.daos.itemDao;

import com.miage.bigdata.daos.dbDao.DocumentDbDao;

public class TodoDaoFactory {
    private static TodoDao myTodoDao;

    public static TodoDao getDao() {
        if (myTodoDao == null) {
            myTodoDao = new DocumentDbDao();
        }
        return myTodoDao;
    }
}
