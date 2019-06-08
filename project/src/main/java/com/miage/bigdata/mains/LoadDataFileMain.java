package com.miage.bigdata.mains;

import com.miage.bigdata.daos.itemDao.document.OrderObjectDao;
import com.miage.bigdata.daos.itemDao.document.ProductObjectDao;
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

        /*CsvLoader csvLoader = new CsvLoader();
        List feedbackItems = csvLoader.load(FeedbackItem.class, new FeedbackItem().getPathFileData());
        for (Object feedbackItem : feedbackItems) {
            System.out.println("[CSV] feedback" + feedbackItem);
        }*/

        XmlLoader xmlLoader = new XmlLoader();
        List invoiceItems = xmlLoader.load(InvoiceItem.class, new InvoiceItem().getPathFileData());
        for (Object invoiceItem : invoiceItems) {
            System.out.println("[XML] invoice" + invoiceItem);
        }
    }
}
