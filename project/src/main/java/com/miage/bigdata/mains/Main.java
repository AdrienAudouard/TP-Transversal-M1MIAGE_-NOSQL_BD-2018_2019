package com.miage.bigdata.mains;

import com.miage.bigdata.daos.loader.CsvLoader;
import com.miage.bigdata.daos.loader.JsonLoader;
import com.miage.bigdata.daos.loader.XmlLoader;
import com.miage.bigdata.models.column.InvoiceItem;
import com.miage.bigdata.models.column.InvoicesItem;
import com.miage.bigdata.models.document.OrderItem;
import com.miage.bigdata.models.keyvalue.FeedbackItem;

import java.lang.reflect.Field;
import java.util.List;

public class Main {

    public static void main(String args[]) {
        /*JsonLoader jsonLoader = new JsonLoader();
        List orderItems = jsonLoader.load(OrderItem.class, new OrderItem().getPathFileData(), null);
        for (Object orderItem : orderItems) {
            System.out.println("[JSON] order" + orderItem);
        }*/

        /*CsvLoader csvLoader = new CsvLoader();
        List feedbackItems = csvLoader.load(
                FeedbackItem.class,
                new FeedbackItem().getPathFileData(),
                getColumns(FeedbackItem.class)
        );
        for (Object feedbackItem : feedbackItems) {
            System.out.println("[CSV] feedback" + feedbackItem);
        }*/

        XmlLoader xmlLoader = new XmlLoader();
        InvoicesItem invoiceItems = (InvoicesItem) xmlLoader.load(InvoicesItem.class, new InvoiceItem().getPathFileData(), true);
        for (InvoiceItem invoiceItem : invoiceItems.getInvoiceItems()) {
            System.out.println("[XML] invoice" + invoiceItem);
        }
    }

    private static String[] getColumns(Class cl) {
        Field[] fields = cl.getDeclaredFields();
        String[] columns = new String[fields.length];

        //for (int i = 0; i < fields.length; i++) {
        for (int i = 0; i < fields.length; i++) {
            columns[i] = fields[i].getName();
            System.out.println("col: " + columns[i]);
        }

        return columns;
    }

}
