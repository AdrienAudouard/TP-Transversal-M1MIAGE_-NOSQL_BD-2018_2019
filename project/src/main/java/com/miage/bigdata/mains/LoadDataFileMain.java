package com.miage.bigdata.mains;

import com.miage.bigdata.controllers.item.ItemController;
import com.miage.bigdata.controllers.models.*;
import com.miage.bigdata.models.column.InvoiceItem;
import com.miage.bigdata.models.column.InvoiceLine;
import com.miage.bigdata.models.document.OrderItem;
import com.miage.bigdata.models.document.ProductItem;
import com.miage.bigdata.models.keyvalue.FeedbackItem;

import java.util.List;

public class LoadDataFileMain {
    private static ModelController modelController = new ModelController();

    private static ItemController<OrderItem> orderItemItemController = modelController.getItemController(OrderItem.class);
    private static ItemController<ProductItem> productItemItemController = modelController.getItemController(ProductItem.class);
    private static ItemController<FeedbackItem> feedbackItemItemController = modelController.getItemController(FeedbackItem.class);
    private static ItemController<InvoiceLine> invoiceLineItemController = modelController.getItemController(InvoiceLine.class);
    private static ItemController<InvoiceItem> invoiceItemItemController = modelController.getItemController(InvoiceItem.class);

    public static void main(String args[]) {
        //initController();

        //loadOrders();

        //loadProducts();

        loadFeedbacks();

        //loadInvoices();

        /*List invoiceItems = new InvoiceObjectDao().loadDataFile();
        for (Object invoiceItem : invoiceItems) {
            System.out.println("[XML][InvoiceObjectDao] invoice" + invoiceItem);
        }*/
    }

    public static void initController() {
        ItemController[] controllers = new ItemController[]{orderItemItemController,
                productItemItemController,
                feedbackItemItemController,
                invoiceItemItemController,
                invoiceLineItemController
        };

        for (ItemController ctrl : controllers) {
            ctrl.deleteTable();
            ctrl.createTable();
        }
    }

    public static void loadOrders() {
        System.out.println("Loading Orders data on DB...");

        orderItemItemController.populateTable();

        List items = orderItemItemController.readAll();
        for (Object item : items) {
            System.out.println("[Orders][Azure]: " + item);
        }
    }

    public static void loadProducts() {
        System.out.println("Loading Products data on DB...");

        productItemItemController.populateTable();

        List items = productItemItemController.readAll();
        for (Object item : items) {
            System.out.println("[Products][Azure]: " + item);
        }
    }

    // TODO : Waiting Victor for run it
    public static void loadFeedbacks() {
        System.out.println("Loading Feedbacks data on DB...");

        feedbackItemItemController.populateTable();

        List items = feedbackItemItemController.readAll();
        for (Object item : items) {
            System.out.println("[Feedbacks][Azure]: " + item);
        }
    }

    public static void loadInvoices() {

        System.out.println("Loading Invoices data on DB...");

        invoiceItemItemController.populateTable();

        List items = invoiceItemItemController.readAll();
        for (Object item : items) {
            System.out.println("[Invoices][Azure]: " + item);
        }
    }
}
