package com.miage.bigdata.daos.itemDao.example;

public class TodoDaoFactory {
    private static TodoDao myTodoDao;

    public static TodoDao getDao() {
        /*
        if (myTodoDao == null) {
            myTodoDao = new RelationalModelDbDao();
        }
        return myTodoDao; */
        return null;
    }
}
