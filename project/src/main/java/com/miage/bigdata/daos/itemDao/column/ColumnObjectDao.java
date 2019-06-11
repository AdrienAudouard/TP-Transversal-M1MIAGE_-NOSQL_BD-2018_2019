package com.miage.bigdata.daos.itemDao.column;

import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.miage.bigdata.daos.dbDao.column.ColumnModelDbDao;
import com.miage.bigdata.daos.itemDao.ObjectDao;
import com.miage.bigdata.models.column.ColumnItem;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
/**
 * With Cassandra and Cosmos DB it's not possible to query data with a filter on a non indexed field.
 * So it's only possible to filter on the primary key, Cosmos DB does not support yet index with Cassandra
 *
 * If we need a filter on a specific field, we must get all the table and filter the result. In this case,
 * the best method is to use parallelStream, it provide the best performances on large list.
 *
 * Example: InvoiceObjectDao.getLinesByInvoice
 *
 */
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
