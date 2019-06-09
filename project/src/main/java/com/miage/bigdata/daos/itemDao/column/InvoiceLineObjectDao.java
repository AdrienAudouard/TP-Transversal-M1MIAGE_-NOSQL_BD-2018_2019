package com.miage.bigdata.daos.itemDao.column;

import com.miage.bigdata.daos.dbDao.column.ColumnModelDbDao;
import com.miage.bigdata.models.column.InvoiceLine;

import java.util.List;

// TODO : Implements methods + constructor(s)
public class InvoiceLineObjectDao extends ColumnObjectDao<InvoiceLine> {

    public InvoiceLineObjectDao(ColumnModelDbDao dbDao) {
        super(dbDao);
    }

    @Override
    protected String getDatabaseID() {
        return null;
    }

    @Override
    public List<InvoiceLine> readAll() {
        return null;
    }

    @Override
    public InvoiceLine create(InvoiceLine item) {
        return null;
    }

    @Override
    public InvoiceLine getByID(String id) {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public InvoiceLine update(InvoiceLine item) {
        return null;
    }

    @Override
    public String generateID() {
        return null;
    }

    @Override
    public boolean createTable() {
        return false;
    }

    @Override
    public boolean deleteTable() {
        return false;
    }

    @Override
    protected Class<InvoiceLine> getItemClass() {
        return InvoiceLine.class;
    }
}
