package com.miage.bigdata.mains;

import com.miage.bigdata.controllers.item.GraphItemController;
import com.miage.bigdata.controllers.item.ItemController;
import com.miage.bigdata.controllers.models.ModelController;
import com.miage.bigdata.models.column.InvoiceItem;
import com.miage.bigdata.models.column.InvoiceLine;
import com.miage.bigdata.models.document.OrderItem;
import com.miage.bigdata.models.document.ProductItem;
import com.miage.bigdata.models.graph.Person;
import com.miage.bigdata.models.graph.Post;
import com.miage.bigdata.models.keyvalue.FeedbackItem;

import java.util.List;

public class LoadDataFileMain {
    private static ModelController modelController = new ModelController();

    private static ItemController<OrderItem> orderItemItemController = modelController.getItemController(OrderItem.class);
    private static ItemController<ProductItem> productItemItemController = modelController.getItemController(ProductItem.class);
    private static ItemController<FeedbackItem> feedbackItemItemController = modelController.getItemController(FeedbackItem.class);
    private static ItemController<InvoiceLine> invoiceLineItemController = modelController.getItemController(InvoiceLine.class);
    private static ItemController<InvoiceItem> invoiceItemItemController = modelController.getItemController(InvoiceItem.class);
    private static GraphItemController<Post> postGraphItemController = (GraphItemController<Post>) modelController.getItemController(Post.class);
    private static GraphItemController<Person> personGraphItemController = (GraphItemController<Person>) modelController.getItemController(Person.class);

    public static void main(String args[]) {
        initController();

        //loadPost();

        loadGraphData();

        //loadOrders();

        //loadProducts();

        //loadFeedbacks();

        //loadInvoices();

        /*List invoiceItems = new InvoiceObjectDao().loadDataFile();
        for (Object invoiceItem : invoiceItems) {
            System.out.println("[XML][InvoiceObjectDao] invoice" + invoiceItem);
        }*/
    }

    public static void loadGraphData() {
        personGraphItemController.populateTable();
        postGraphItemController.populateTable();

        System.out.println(personGraphItemController.readAll().size() + "persons found on db");
        System.out.println(postGraphItemController.readAll().size() + "posts found on db");

        List<Post> posts = postGraphItemController.readAll();
        List<Person> persons = personGraphItemController.readAll();

        personGraphItemController.createPersonKnowsPerson(persons);

        personGraphItemController.createpostHasCreator(persons, posts);
    }

    public static void initController() {
        ItemController[] controllers = new ItemController[]{//orderItemItemController,
               // productItemItemController,
               // feedbackItemItemController,
                //invoiceItemItemController,
                //invoiceLineItemController,
                postGraphItemController,
                personGraphItemController
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
