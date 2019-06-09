package com.miage.bigdata.controllers;

import com.miage.bigdata.daos.dbDao.column.ColumnModelDbDao;
import com.miage.bigdata.daos.itemDao.column.InvoiceLineObjectDao;
import com.miage.bigdata.daos.itemDao.column.InvoiceObjectDao;
import com.miage.bigdata.models.column.InvoiceItem;
import com.miage.bigdata.models.column.InvoiceLine;

public class ColumnController extends ModelDbController<ColumnModelDbDao> {
    public ColumnController() {
        super();

        this.dbDao = new ColumnModelDbDao();
        this.itemControllers.put(InvoiceItem.class, new ItemController<InvoiceItem>(new InvoiceObjectDao(dbDao)));
        this.itemControllers.put(InvoiceLine.class, new ItemController<InvoiceLine>(new InvoiceLineObjectDao(dbDao)));
    }
}
