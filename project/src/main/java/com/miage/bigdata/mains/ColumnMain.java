package com.miage.bigdata.mains;

import com.miage.bigdata.daos.dbDao.column.ColumnModelDbDao;
import com.miage.bigdata.daos.itemDao.column.InvoiceObjectDao;
import com.miage.bigdata.models.column.InvoiceItem;

import java.util.Date;

public class ColumnMain {
    public static void main(String[] args) {
        System.out.println(System.getProperty("java.home"));
        ColumnModelDbDao columnModelDbDao = new ColumnModelDbDao();
        InvoiceObjectDao invoiceObjectDao = new InvoiceObjectDao(columnModelDbDao);

        System.out.println("------------ Delete table ------------");

        System.out.println("Success: " + invoiceObjectDao.deleteTable());

        System.out.println("------------ Create table ------------");

        System.out.println("Success: " + invoiceObjectDao.createTable());

        System.out.println("------------ Add Items ------------");

        InvoiceItem i1 = new InvoiceItem("1", "2", new Date(), 10.0);
        InvoiceItem i2 = new InvoiceItem("2", "3", new Date(), 10.0);
        InvoiceItem i3 = new InvoiceItem("3", "4", new Date(), 10.0);
        InvoiceItem i4 = new InvoiceItem("4", "5", new Date(), 10.0);

        invoiceObjectDao.create(i1, i2, i3, i3, i4);

        System.out.println(invoiceObjectDao.readAll().toString());

        System.out.println("------------ Get item with ID 3 ------------");

        System.out.println(invoiceObjectDao.getByID("3").toString());

        System.out.println("------------ Delete item with ID 3 ------------");

        invoiceObjectDao.delete("3");

        System.out.println(invoiceObjectDao.readAll().toString());

        System.out.println("------------ Update item with ID 2 ------------");

        i2.setPersonId("99");

        System.out.println(invoiceObjectDao.update(i2));

    }
}
