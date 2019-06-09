package com.miage.bigdata.daos.itemDao.column;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Row;
import com.miage.bigdata.daos.dbDao.column.ColumnModelDbDao;
import com.miage.bigdata.models.column.InvoiceItem;
import com.miage.bigdata.models.column.InvoiceLine;

import java.util.ArrayList;
import java.util.List;

public class InvoiceObjectDao extends ColumnObjectDao<InvoiceItem> {
    private PreparedStatement insertInvoiceStatement;
    private PreparedStatement insertLineStatement;
    private PreparedStatement getLineStatement;
    private PreparedStatement getByIdStatement;
    private PreparedStatement deleteInvoiceStatement;
    private PreparedStatement deleteLineStatement;
    private PreparedStatement updateInvoiceStatement;
    private PreparedStatement updateLineStatement;

    public InvoiceObjectDao(ColumnModelDbDao dbDao) {
        super(dbDao);


        /**
         * Query must be prepared once
         */
        insertInvoiceStatement = this.prepareInsertStatement( "INSERT INTO " + getDatabaseID() + "(orderId, personId, orderDate, totalPrice, orderLine) VALUES(?, ?, ?, ?, ?)");
        insertLineStatement = this.prepareInsertStatement("INSERT INTO invoiceLine (productId, orderId, asin, title, price, brand) VALUES(?, ?, ?, ?, ?, ?)");
        getByIdStatement = this.prepareInsertStatement("SELECT * FROM " + getDatabaseID() + " WHERE orderId = ?");
        deleteInvoiceStatement = this.prepareInsertStatement("DELETE FROM " + getDatabaseID() + " WHERE orderId = ?");
        deleteLineStatement = this.prepareInsertStatement("DELETE FROM invoiceLine WHERE productId = ?");
        getLineStatement = this.prepareInsertStatement("SELECT * FROM invoiceLine WHERE productId = ?");
        updateInvoiceStatement = this.prepareInsertStatement("UPDATE " + getDatabaseID() + " SET personId = ?, orderDate = ?, " +
                "totalPrice = ?, orderLine = ? WHERE orderId = ?");
        updateLineStatement = this.prepareInsertStatement("UPDATE invoiceLine SET asin = ?, title = ?, price = ?, brand = ? WHERE productId = ?");
    }

    public InvoiceObjectDao() {
    }

    @Override
    protected String getDatabaseID() {
        return "invoice";
    }

    private Row getLineById(String orderId) {
        BoundStatement boundStatement = new BoundStatement(getLineStatement);
        return cassandraSession.execute(boundStatement.bind(orderId)).one();
    }

    @Override
    public List<InvoiceItem> readAll() {
        String query = "SELECT * FROM " + getDatabaseID();


        List<Row> rows = cassandraSession.execute(query).all();

        List<InvoiceItem> items = new ArrayList<>();

        for (Row row : rows) {
            Row lineRow = getLineById(row.getString("orderId"));
            items.add(instanciateItemFromRow(row, lineRow));
        }

        return items;
    }

    @Override
    public InvoiceItem create(InvoiceItem item) {
        InvoiceLine invoiceLine = item.getOrderLine().get(0);


        BoundStatement boundStatement = new BoundStatement(insertInvoiceStatement);
        cassandraSession.execute(boundStatement.bind(item.getId(),
                item.getPersonId(),
                item.getOrderDate(),
                item.getTotalPrice(),
                invoiceLine.getId()));

        boundStatement = new BoundStatement(insertLineStatement);
        cassandraSession.execute(boundStatement.bind(invoiceLine.getId(),
                item.getId(),
                invoiceLine.getAsin(),
                invoiceLine.getTitle(),
                invoiceLine.getPrice(),
                invoiceLine.getBrand()));

        return item;
    }

    @Override
    public InvoiceItem getByID(String id) {
        BoundStatement boundStatement = new BoundStatement(getByIdStatement);
        Row row = cassandraSession.execute(boundStatement.bind(id)).one();

        if (row == null) {
            return null;
        }

        Row lineRow = getLineById(row.getString("orderId"));

        return instanciateItemFromRow(row, lineRow);
    }

    @Override
    public boolean delete(String id) {
        Row row = getLineById(id);

        BoundStatement boundStatement = new BoundStatement(deleteInvoiceStatement);
        cassandraSession.execute(boundStatement.bind(id));

        boundStatement = new BoundStatement(deleteLineStatement);
        cassandraSession.execute(boundStatement.bind(row.getString("productId")));

        return true;
    }

    @Override
    public InvoiceItem update(InvoiceItem item) {
        InvoiceLine line = item.getOrderLine().get(0);
        BoundStatement boundStatement = new BoundStatement(updateInvoiceStatement);
        cassandraSession.execute(boundStatement.bind(
                item.getPersonId(),
                item.getOrderDate(),
                item.getTotalPrice(),
                item.getOrderLine().get(0).getId(),
                item.getId()));

        boundStatement = new BoundStatement(updateLineStatement);
        cassandraSession.execute(boundStatement.bind(
                line.getAsin(),
                line.getTitle(),
                line.getPrice(),
                line.getBrand(),
                line.getId()
        ));

        return getByID(item.getId());
    }

    @Override
    public String generateID() {
        return null;
    }

    @Override
    public boolean createTable() {
        String createInvoice = "CREATE TABLE IF NOT EXISTS " + getDatabaseID() + " (\n" +
                "\torderId TEXT PRIMARY KEY,\n" +
                "\tpersonId TEXT,\n" +
                "\torderDate TIMESTAMP,\n" +
                "\ttotalPrice DOUBLE,\n" +
                "\torderLine TEXT\n" +
                ");";

        String createLine = "CREATE TABLE IF NOT EXISTS invoiceLine (" +
                "productId TEXT PRIMARY KEY, " +
                "orderId TEXT, " +
                "asin TEXT, " +
                "title TEXT, " +
                "price DOUBLE, " +
                "brand TEXT);";

        this.cassandraSession.execute(createInvoice);
        this.cassandraSession.execute(createLine);

        return true;
    }

    @Override
    public boolean populateTable() {
        return false;
    }

    @Override
    public boolean deleteTable() {
        String query = "DROP TABLE IF EXISTS " + getDatabaseID() + ";";
        String query2 = "DROP TABLE IF EXISTS invoiceLine;";

        this.cassandraSession.execute(query);
        this.cassandraSession.execute(query2);

        return true;
    }

    @Override
    protected Class<InvoiceItem> getItemClass() {
        return InvoiceItem.class;
    }
}
