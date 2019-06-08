package com.miage.bigdata.daos.itemDao.column;

import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.miage.bigdata.daos.dbDao.column.ColumnModelDbDao;
import com.miage.bigdata.daos.itemDao.ObjectDao;
import com.miage.bigdata.models.column.ColumnItem;

import java.lang.reflect.InvocationTargetException;

abstract class ColumnObjectDao<T extends ColumnItem> extends ObjectDao<T, ColumnModelDbDao> {
    protected final Session cassandraSession;

    public ColumnObjectDao(ColumnModelDbDao dbDao) {
        super(dbDao);

        this.cassandraSession = dbDao.connect();
    }

    public PreparedStatement prepareInsertStatement(String query) {
        return cassandraSession.prepare(query);
    }

    protected T instanciateItemFromRow(Row row, Row lineRow) {
        try {
            return getItemClass().getConstructor(Row.class, Row.class).newInstance(row, lineRow);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }
}
