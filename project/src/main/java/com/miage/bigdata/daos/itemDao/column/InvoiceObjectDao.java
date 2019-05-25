package com.miage.bigdata.daos.itemDao.column;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Row;
import com.miage.bigdata.daos.dbDao.column.ColumnModelDbDao;
import com.miage.bigdata.models.column.InvoiceItem;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public class InvoiceObjectDao extends ColumnObjectDao<InvoiceItem> {
    public InvoiceObjectDao(ColumnModelDbDao dbDao) {
        super(dbDao);
    }

    @Override
    protected String getDatabaseID() {
        return "invoice";
    }

    @Override
    public List<InvoiceItem> readAll() {
        String query = "SELECT * FROM " + getDatabaseID();
        List<Row> rows = cassandraSession.execute(query).all();

        List<InvoiceItem> items = new ArrayList<>();

        for (Row row : rows) {
            items.add(instanciateItemFromRow(row));
        }

        return items;
    }

    @Override
    public InvoiceItem create(@NonNull InvoiceItem item) {
        String query = "INSERT INTO " + getDatabaseID() + "(orderId, personId, orderDate, totalPrice, orderLine) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = prepareInsertStatement(query);
        BoundStatement boundStatement = new BoundStatement(preparedStatement);
        cassandraSession.execute(boundStatement.bind(
                item.getId(),
                item.getPersonId(),
                item.getOrderDate(),
                item.getTotalPrice(),
                1
        ));

        return item;
    }

    @Override
    public InvoiceItem getByID(@NonNull String id) {
        String query  = "SELECT * FROM " + getDatabaseID() + " WHERE orderId = ?";

        PreparedStatement preparedStatement = prepareInsertStatement(query);
        BoundStatement boundStatement = new BoundStatement(preparedStatement);
        Row row = cassandraSession.execute(boundStatement.bind(id)).one();

        if (row == null) {
            return null;
        }

        return instanciateItemFromRow(row);
    }

    @Override
    public boolean delete(@NonNull String id) {
        String query = "DELETE FROM " + getDatabaseID() + " WHERE orderId = ?";

        PreparedStatement preparedStatement = prepareInsertStatement(query);
        BoundStatement boundStatement = new BoundStatement(preparedStatement);
        cassandraSession.execute(boundStatement.bind(id));

        return true;
    }

    @Override
    public InvoiceItem update(@NonNull InvoiceItem item) {
        String query  = "UPDATE " + getDatabaseID() + " SET personId = ?, orderDate = ?, " +
                "totalPrice = ?, orderLine = ? WHERE orderId = ?";

        PreparedStatement preparedStatement = prepareInsertStatement(query);
        BoundStatement boundStatement = new BoundStatement(preparedStatement);
        cassandraSession.execute(boundStatement.bind(
                item.getPersonId(),
                item.getOrderDate(),
                item.getTotalPrice(),
                1,
                item.getId()));

        return item;
    }

    @Override
    public String generateID() {
        return null;
    }

    @Override
    public boolean createTable() {
        String query = "CREATE TABLE IF NOT EXISTS " + getDatabaseID() + " (\n" +
                "\torderId TEXT PRIMARY KEY,\n" +
                "\tpersonId TEXT,\n" +
                "\torderDate TIMESTAMP,\n" +
                "\ttotalPrice DOUBLE,\n" +
                "\torderLine INT\n" +
                ");";

        this.cassandraSession.execute(query);

        return true;
    }

    @Override
    public boolean populateTable() {
        return false;
    }

    @Override
    public boolean deleteTable() {
        String query = "DROP TABLE IF EXISTS " + getDatabaseID() + ";";

        this.cassandraSession.execute(query);

        return true;
    }

    @Override
    protected Class<InvoiceItem> getItemClass() {
        return InvoiceItem.class;
    }
}
