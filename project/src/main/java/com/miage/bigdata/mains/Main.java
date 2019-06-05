package com.miage.bigdata.mains;

import com.miage.bigdata.daos.itemDao.document.OrderObjectDao;
import com.miage.bigdata.daos.loader.CsvLoader;
import com.miage.bigdata.daos.loader.JsonLoader;
import com.miage.bigdata.daos.loader.XmlLoader;
import com.miage.bigdata.models.column.InvoiceItem;
import com.miage.bigdata.models.column.InvoicesItem;
import com.miage.bigdata.models.document.OrderItem;
import com.miage.bigdata.models.document.ProductItem;
import com.miage.bigdata.models.keyvalue.FeedbackItem;

import java.lang.reflect.Field;
import java.util.List;

public class Main {

    public static void main(String args[]) {
        /*JsonLoader jsonLoader = new JsonLoader();
        List orderItems = jsonLoader.load(OrderItem.class, new OrderItem().getPathFileData());
        for (Object orderItem : orderItems) {
            System.out.println("[JSON] order" + orderItem);
        }*/

        /*CsvLoader csvLoader = new CsvLoader();
        List productItems = csvLoader.load(ProductItem.class, new ProductItem().getPathFileData());
        for (Object productItem : productItems) {
            System.out.println("[CSV] product" + productItem);
        }*/

        /*List feedbackItems = csvLoader.load(FeedbackItem.class, new FeedbackItem().getPathFileData());
        for (Object feedbackItem : feedbackItems) {
            System.out.println("[CSV] feedback" + feedbackItem);
        }*/

        /*XmlLoader xmlLoader = new XmlLoader();
        InvoicesItem invoiceItems = (InvoicesItem) xmlLoader.load(InvoicesItem.class, new InvoiceItem().getPathFileData(), true);
        for (InvoiceItem invoiceItem : invoiceItems.getInvoiceItems()) {
            System.out.println("[XML] invoice" + invoiceItem);
        }*/

        List orderItems = new OrderObjectDao().loadDataFile();
        for (Object orderItem : orderItems) {
            System.out.println("[JSON][OrderObjectDao] order" + orderItem);
        }
    }
}
