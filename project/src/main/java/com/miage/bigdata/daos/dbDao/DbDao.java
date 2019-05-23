package com.miage.bigdata.daos.dbDao;

import com.miage.bigdata.daos.itemDao.ObjectDao;

import java.util.List;

public abstract class DbDao<T> {
    protected List<ObjectDao> objectDaos;

    public abstract T connect();

    public abstract boolean isConnected();
}
