package com.miage.bigdata.mains;

import com.miage.bigdata.daos.itemDao.column.InvoiceObjectDao;
import com.miage.bigdata.daos.itemDao.document.OrderObjectDao;
import com.miage.bigdata.daos.itemDao.document.ProductObjectDao;
import com.miage.bigdata.daos.itemDao.keyvalue.FeedbackObjectDao;
import com.miage.bigdata.daos.loader.CsvLoader;
import com.miage.bigdata.daos.loader.XmlLoader;
import com.miage.bigdata.models.column.InvoiceItem;
import com.miage.bigdata.models.keyvalue.FeedbackItem;

import java.util.List;

public class LoadDataFileMain {

    public static void main(String args[]) {
        /*List orderItems = new OrderObjectDao().loadDataFile();
        for (Object orderItem : orderItems) {
            System.out.println("[JSON][OrderObjectDao] order" + orderItem);
        }*/

        /*List productItems = new ProductObjectDao().loadDataFile();
        for (Object productItem : productItems) {
            System.out.println("[CSV][ProductObjectDao] product" + productItem);
        }*/

        /*List feedbackItems = new FeedbackObjectDao().loadDataFile();
        for (Object feedbackItem : feedbackItems) {
            System.out.println("[CSV][FeedbackObjectDao] feedback" + feedbackItem);
        }*/

        List invoiceItems = new InvoiceObjectDao().loadDataFile();
        for (Object invoiceItem : invoiceItems) {
            System.out.println("[XML][InvoiceObjectDao] invoice" + invoiceItem);
        }
    }
}
