package com.miage.bigdata.mains;

import com.miage.bigdata.daos.dbDao.column.ColumnModelDbDao;
import com.miage.bigdata.daos.itemDao.column.InvoiceObjectDao;
import com.miage.bigdata.models.column.InvoiceItem;
import com.miage.bigdata.models.column.InvoiceLine;

import java.util.Date;

public class ColumnMain {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(System.getProperty("java.home"));
        ColumnModelDbDao columnModelDbDao = new ColumnModelDbDao();
        InvoiceObjectDao invoiceObjectDao = new InvoiceObjectDao(columnModelDbDao);

        InvoiceLine line1 = new InvoiceLine("1", "1", "Line 1", 10.0, "Brand 1");
        InvoiceItem i1 = new InvoiceItem("1", "2", new Date(), 10.0, line1);

        InvoiceLine line2 = new InvoiceLine("2", "2", "Line 2", 15.0, "Brand 2");
        InvoiceItem i2 = new InvoiceItem("2", "2", new Date(), 20.0, line2);

        System.out.println("------------ Delete table ------------");

        System.out.println("Success: " + invoiceObjectDao.deleteTable());

        Thread.sleep(1000);

        System.out.println("------------ Create table ------------");

        System.out.println("Success: " + invoiceObjectDao.createTable());

        Thread.sleep(1000);

        System.out.println("------------ Add Items ------------");

        invoiceObjectDao.create(i1, i2);

        System.out.println(invoiceObjectDao.readAll().toString());

        System.out.println("------------ Get item with ID 2 ------------");

        System.out.println(invoiceObjectDao.getByID("2").toString());

        System.out.println("------------ Update item with ID 2 ------------");
        i2.setPersonId("99");
        line2.setBrand("ljnjkhjkj");
        System.out.println(invoiceObjectDao.update(i2).toString());

        System.out.println("------------ Delete item with ID 2 ------------");

        invoiceObjectDao.delete("2");

        System.out.println(invoiceObjectDao.readAll().toString());

    }
}
