package com.miage.bigdata.daos;

import com.miage.bigdata.daos.DocDbDao;
import com.miage.bigdata.daos.TodoDao;

public class TodoDaoFactory {
    private static TodoDao myTodoDao;

    public static TodoDao getDao() {
        if (myTodoDao == null) {
            myTodoDao = new DocDbDao();
        }
        return myTodoDao;
    }
}
