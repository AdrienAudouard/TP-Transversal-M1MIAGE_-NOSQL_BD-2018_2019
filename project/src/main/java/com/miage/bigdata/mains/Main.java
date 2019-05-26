package com.miage.bigdata.mains;

import com.miage.bigdata.daos.loader.XmlLoader;
import com.miage.bigdata.models.column.InvoiceItem;
import com.miage.bigdata.models.column.InvoicesItem;

public class Main {

    public static void main(String args[]) {
        /*JsonLoader jsonLoader = new JsonLoader();
        List orderItems = jsonLoader.load(OrderItem[].class, new OrderItem().getPathFileData());
        for (Object orderItem : orderItems) {
            System.out.println("[JSON] order" + orderItem);
        }*/

        /*CsvLoader csvLoader = new CsvLoader();
        List feedbackItems = csvLoader.load(FeedbackItem[].class, new FeedbackItem().getPathFileData());
        for (Object feedbackItem : feedbackItems) {
            System.out.println("[CSV] feedback" + feedbackItem);
        }*/

        XmlLoader xmlLoader = new XmlLoader();
        InvoicesItem invoiceItems = (InvoicesItem) xmlLoader.load(InvoicesItem.class, new InvoiceItem().getPathFileData(), true);
        for (InvoiceItem invoiceItem : invoiceItems.getInvoiceItems()) {
            System.out.println("[XML] invoice" + invoiceItem);
        }
    }

}
