package com.miage.bigdata.daos.itemDao.column;

import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.miage.bigdata.daos.dbDao.column.ColumnModelDbDao;
import com.miage.bigdata.daos.itemDao.ObjectDao;
import com.miage.bigdata.models.column.ColumnItem;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

abstract class ColumnObjectDao<T extends ColumnItem> extends ObjectDao<T, ColumnModelDbDao> {
    protected Session cassandraSession;

    public ColumnObjectDao(ColumnModelDbDao dbDao) {
        super(dbDao);

        this.cassandraSession = dbDao.connect();
    }

    public ColumnObjectDao() {
    }

    public PreparedStatement prepareInsertStatement(String query) {
        return cassandraSession.prepare(query);
    }

    protected T instanciateItemFromRow(Row row, List<Row> lineRow) {
        try {
            return getItemClass().getConstructor(Row.class, List.class).newInstance(row, lineRow);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }
}
