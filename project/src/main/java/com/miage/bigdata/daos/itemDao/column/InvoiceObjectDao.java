package com.miage.bigdata.daos.itemDao.column;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Row;
import com.miage.bigdata.controllers.ColumnController;
import com.miage.bigdata.controllers.ItemController;
import com.miage.bigdata.daos.dbDao.column.ColumnModelDbDao;
import com.miage.bigdata.models.column.InvoiceItem;
import com.miage.bigdata.models.column.InvoiceLine;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * With Cassandra and Cosmos DB it's not possible to query data with a filter on a non indexed field.
 * So it's only possible to filter on the primary key.
 *
 * If we need a filter on a specific field, we must get all the table and filter the result. In this case,
 * the best method is to use parallelStream, it provide the best performances on large list.
 *
 * Example: InvoiceObjectDao.getLinesByInvoice
 *
 */
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
    }

    private void prepareStatements() {
        if (insertInvoiceStatement != null) {
            return;
        }

        /**
         * Query must be prepared once
         */
        insertInvoiceStatement = this.prepareInsertStatement( "INSERT INTO " + getDatabaseID() + "(orderId, personId, orderDate, totalPrice) VALUES(?, ?, ?, ?)");
        insertLineStatement = this.prepareInsertStatement("INSERT INTO invoiceLine (productId, orderId, asin, title, price, brand) VALUES(?, ?, ?, ?, ?, ?)");
        getByIdStatement = this.prepareInsertStatement("SELECT * FROM " + getDatabaseID() + " WHERE orderId = ?");
        deleteInvoiceStatement = this.prepareInsertStatement("DELETE FROM " + getDatabaseID() + " WHERE orderId = ?");
        deleteLineStatement = this.prepareInsertStatement("DELETE FROM invoiceLine WHERE productId = ?");
        getLineStatement = this.prepareInsertStatement("SELECT * FROM invoiceLine WHERE productId = ?");
        updateInvoiceStatement = this.prepareInsertStatement("UPDATE " + getDatabaseID() + " SET personId = ?, orderDate = ?, " +
                "totalPrice = ? WHERE orderId = ?");

        updateLineStatement = this.prepareInsertStatement("UPDATE invoiceLine SET asin = ?, title = ?, price = ?, brand = ? WHERE productId = ?");
    }

    public InvoiceObjectDao() {
    }

    @Override
    protected String getDatabaseID() {
        return "invoice";
    }

    private Row getLineById(String orderId) {
        prepareStatements();

        BoundStatement boundStatement = new BoundStatement(getLineStatement);
        return cassandraSession.execute(boundStatement.bind(orderId)).one();
    }

    @Override
    public List<InvoiceItem> readAll() {
        prepareStatements();

        String query = "SELECT * FROM " + getDatabaseID();


        List<Row> rows = cassandraSession.execute(query).all();

        List<InvoiceItem> items = new ArrayList<>();

        for (Row row : rows) {
            List<Row> lines = getLinesByInvoice(row.getString("orderId"));
            items.add(instanciateItemFromRow(row, lines));
        }

        return items;
    }

    private List<Row> getLinesByInvoice(String invoiceId) {
        prepareStatements();

        String query = "SELECT * FROM invoiceLine";
        List<Row> rows = cassandraSession.execute(query).all();

        return rows.parallelStream().filter(row -> invoiceId.equals(row.getString("orderId"))).collect(Collectors.toList());
    }

    @Override
    public InvoiceItem create(InvoiceItem item) {
        prepareStatements();

        BoundStatement boundStatement = new BoundStatement(insertInvoiceStatement);
        cassandraSession.execute(boundStatement.bind(item.getId(),
                item.getPersonId(),
                item.getOrderDate(),
                item.getTotalPrice()));

        for (InvoiceLine line : item.getOrderLine()) {
            boundStatement = new BoundStatement(insertLineStatement);
            cassandraSession.execute(boundStatement.bind(line.getId(),
                    item.getId(),
                    line.getAsin(),
                    line.getTitle(),
                    line.getPrice(),
                    line.getBrand()));
        }

        return item;
    }

    @Override
    public boolean populateTable() {
        prepareStatements();
        List<InvoiceItem> invoices = loadDataFile();
        for (InvoiceItem invoice : invoices) {
            if(invoice != null) {
                //invoice.setId(generateID());
                List<InvoiceLine> orderLines = invoice.getOrderLine();

                if(orderLines.size() > 0) {
                    ColumnController columnController = new ColumnController();
                    ItemController<InvoiceLine> ilController = columnController.getItemController(InvoiceLine.class);

                    for (InvoiceLine orderLine : orderLines) {
                        orderLine.setId(generateID());
                        ilController.create(orderLine);
                    }
                }
                create(invoice);
            }
        }
        return true;
    }

    @Override
    public InvoiceItem getByID(String id) {
        prepareStatements();
        BoundStatement boundStatement = new BoundStatement(getByIdStatement);
        Row row = cassandraSession.execute(boundStatement.bind(id)).one();

        if (row == null) {
            return null;
        }

        List<Row> lines = getLinesByInvoice(row.getString("orderId"));

        return instanciateItemFromRow(row, lines);
    }

    @Override
    public boolean delete(String id) {
        prepareStatements();

        Row row = getLineById(id);

        BoundStatement boundStatement = new BoundStatement(deleteInvoiceStatement);
        cassandraSession.execute(boundStatement.bind(id));

        boundStatement = new BoundStatement(deleteLineStatement);
        cassandraSession.execute(boundStatement.bind(row.getString("productId")));

        return true;
    }

    @Override
    public InvoiceItem update(InvoiceItem item) {
        prepareStatements();

        BoundStatement boundStatement = new BoundStatement(updateInvoiceStatement);
        cassandraSession.execute(boundStatement.bind(
                item.getPersonId(),
                item.getOrderDate(),
                item.getTotalPrice(),
                item.getId()));

        for (InvoiceLine line : item.getOrderLine()) {
            boundStatement = new BoundStatement(updateLineStatement);
            cassandraSession.execute(boundStatement.bind(
                    line.getAsin(),
                    line.getTitle(),
                    line.getPrice(),
                    line.getBrand(),
                    line.getId()
            ));
        }

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
                "\ttotalPrice DOUBLE\n" + ");";

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
