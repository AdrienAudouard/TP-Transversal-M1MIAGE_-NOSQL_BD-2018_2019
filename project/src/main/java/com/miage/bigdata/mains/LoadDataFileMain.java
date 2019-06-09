package com.miage.bigdata.mains;

import com.miage.bigdata.controllers.DocumentController;
import com.miage.bigdata.controllers.ItemController;
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

        loadProducts();

        /*List productItems = new ProductObjectDao().loadDataFile();
        for (Object productItem : productItems) {
            System.out.println("[CSV][ProductObjectDao] product" + productItem);
        }*/

        /*List feedbackItems = new FeedbackObjectDao().loadDataFile();
        for (Object feedbackItem : feedbackItems) {
            System.out.println("[CSV][FeedbackObjectDao] feedback" + feedbackItem);
        }*/

        /*List invoiceItems = new InvoiceObjectDao().loadDataFile();
        for (Object invoiceItem : invoiceItems) {
            System.out.println("[XML][InvoiceObjectDao] invoice" + invoiceItem);
        }*/
    }

    public static void loadOrders() {
        System.out.println("Loading Orders data on DB:");
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
        System.out.println("Loading Products data on DB:");
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
}
