package com.miage.bigdata.mains;

import com.miage.bigdata.daos.loader.CsvLoader;
import com.miage.bigdata.daos.loader.JsonLoader;
import com.miage.bigdata.daos.loader.XmlLoader;
import com.miage.bigdata.models.column.InvoiceItem;
import com.miage.bigdata.models.column.InvoicesItem;
import com.miage.bigdata.models.document.OrderItem;
import com.miage.bigdata.models.keyvalue.FeedbackItem;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

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

        /*XmlLoader xmlLoader = new XmlLoader();
        List invoiceItems = xmlLoader.load(InvoiceItem[].class, new InvoiceItem().getPathFileData());
        for (Object invoiceItem : invoiceItems) {
            System.out.println("[XML] invoice" + invoiceItem);
        }*/

        XmlLoader xmlLoader = new XmlLoader();
        List invoiceItems = xmlLoader.load(InvoicesItem.class, new InvoiceItem().getPathFileData());
        //List invoiceItems = xmlLoader.load(InvoiceItem[].class, new InvoiceItem().getPathFileData());
        for (Object invoiceItem : invoiceItems) {
            System.out.println("[XML] invoice" + invoiceItem);
        }

        /*List invoiceItems = load(new InvoiceItem().getPathFileData());
        //List invoiceItems = xmlLoader.load(InvoiceItem[].class, new InvoiceItem().getPathFileData());
        for (Object invoiceItem : invoiceItems) {
            System.out.println("[XML] invoice" + invoiceItem);
        }*/
    }

    /*public static List<InvoiceItem> load(String path) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(InvoicesItem.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            InvoicesItem result = (InvoicesItem) jaxbUnmarshaller.unmarshal(new File(path));
            System.out.println("[XML]" + result.toString());

            return result.getInvoiceItems();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }*/
}
