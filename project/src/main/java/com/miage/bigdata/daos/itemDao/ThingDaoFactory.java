package com.miage.bigdata.daos.itemDao;

import com.miage.bigdata.daos.clientsDao.DocumentClientDao;
import com.miage.bigdata.daos.dbDao.DocumentDbDao;

public class ThingDaoFactory {

    private static ThingDao myThingDao;

    public static ThingDao getDao() {
        if (myThingDao == null) {
            myThingDao = new DocumentDbDao();
        }
        return myThingDao;
    }
}
