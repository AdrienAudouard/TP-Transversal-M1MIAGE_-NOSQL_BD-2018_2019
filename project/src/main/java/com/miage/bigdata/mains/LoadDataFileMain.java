package com.miage.bigdata.mains;

import com.miage.bigdata.controllers.ColumnController;
import com.miage.bigdata.controllers.DocumentController;
import com.miage.bigdata.controllers.ItemController;
import com.miage.bigdata.controllers.KeyValueController;
import com.miage.bigdata.daos.itemDao.column.InvoiceObjectDao;
import com.miage.bigdata.daos.itemDao.document.OrderObjectDao;
import com.miage.bigdata.daos.itemDao.document.ProductObjectDao;
import com.miage.bigdata.daos.itemDao.keyvalue.FeedbackObjectDao;
import com.miage.bigdata.daos.loader.CsvLoader;
import com.miage.bigdata.daos.loader.XmlLoader;
import com.miage.bigdata.models.column.InvoiceItem;
import com.miage.bigdata.models.document.OrderItem;
import com.miage.bigdata.models.document.ProductItem;
import com.miage.bigdata.models.keyvalue.FeedbackItem;

import java.util.List;

public class LoadDataFileMain {

    public static void main(String args[]) {
        //loadOrders();

        //loadProducts();

        //loadFeedbacks();

        loadInvoices();

        /*List invoiceItems = new InvoiceObjectDao().loadDataFile();
        for (Object invoiceItem : invoiceItems) {
            System.out.println("[XML][InvoiceObjectDao] invoice" + invoiceItem);
        }*/
    }

    public static void loadOrders() {
        System.out.println("Loading Orders data on DB...");
        DocumentController documentController = new DocumentController();
        ItemController<OrderItem> controller = documentController.getItemController(OrderItem.class);

        controller.deleteTable();
        controller.createTable();
        controller.populateTable();

        List items = controller.readAll();
        for (Object item : items) {
            System.out.println("[Orders][Azure]: " + item);
        }
    }

    public static void loadProducts() {
        System.out.println("Loading Products data on DB...");
        DocumentController documentController = new DocumentController();
        ItemController<ProductItem> controller = documentController.getItemController(ProductItem.class);

        controller.deleteTable();
        controller.createTable();
        controller.populateTable();

        List items = controller.readAll();
        for (Object item : items) {
            System.out.println("[Products][Azure]: " + item);
        }
    }

    // TODO : Waiting Victor for run it
    public static void loadFeedbacks() {
        System.out.println("Loading Feedbacks data on DB...");
        KeyValueController keyValueController = new KeyValueController();
        ItemController<FeedbackItem> controller = keyValueController.getItemController(FeedbackItem.class);

        //controller.deleteTable();
        //controller.createTable();
        controller.populateTable();

        List items = controller.readAll();
        for (Object item : items) {
            System.out.println("[Feedbacks][Azure]: " + item);
        }
    }

    public static void loadInvoices() {
        System.out.println("Loading Invoices data on DB...");
        ColumnController columnController = new ColumnController();
        ItemController<InvoiceItem> controller = columnController.getItemController(InvoiceItem.class);

        controller.deleteTable();
        controller.createTable();
        controller.populateTable();

        List items = controller.readAll();
        for (Object item : items) {
            System.out.println("[Invoices][Azure]: " + item);
        }
    }
}
