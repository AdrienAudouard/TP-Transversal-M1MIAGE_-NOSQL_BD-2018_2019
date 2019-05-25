package com.miage.bigdata.mains;

import com.miage.bigdata.controllers.DocumentController;
import com.miage.bigdata.controllers.ItemController;
import com.miage.bigdata.models.document.OrderItem;
import com.miage.bigdata.models.document.ProductItem;

import java.util.ArrayList;
import java.util.Date;

public class DocumentMain {

    public static void main(String args[]) {
        DocumentController documentController = new DocumentController();
        ItemController<ProductItem> piController = documentController.getItemController(ProductItem.class);
        ItemController<OrderItem> oiController = documentController.getItemController(OrderItem.class);

        System.out.println(piController.readAll());
        System.out.println(oiController.readAll());

        piController.deleteTable();
        oiController.deleteTable();

        piController.createTable();
        oiController.createTable();

        ProductItem p1 = new ProductItem(oiController.generateID(), "t1", 10, "", "");
        ProductItem p2 = new ProductItem(oiController.generateID(), "t2", 10, "", "");

        System.out.println(piController.create(p1, p2));

        ArrayList<ProductItem> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);

        OrderItem orderItem = new OrderItem(oiController.generateID(),
                "1",
                new Date(),
                10.0,
                list);

        oiController.create(orderItem);

        /*
        ItemController<ThingItem> tiController = documentController.getItemController(ThingItem.class);

        System.out.println("------------ Delete table ------------");

        System.out.println("Success: " + tiController.deleteTable());

        System.out.println("------------ Create table ------------");

        System.out.println("Success: " + tiController.createTable());


        ThingItem thingItem1 = new ThingItem(tiController.generateID(), "test");
        ThingItem thingItem2 = new ThingItem(tiController.generateID(), "test");
        ThingItem thingItem3 = new ThingItem(tiController.generateID(), "test");
        ThingItem thingItem4 = new ThingItem(tiController.generateID(), "test");


        System.out.println("------------ Print all items ------------");

        System.out.println(tiController.readAll().toString());

        System.out.println("------------ Add items ------------");

        System.out.println(tiController.create(thingItem1, thingItem2, thingItem3, thingItem4).toString());

        System.out.println("------------ Get item with id 3 ------------");

        System.out.println(tiController.getByID("3").toString());

        System.out.println("------------ Update item with id 3 ------------");

        thingItem2.setName("Test 2");

        System.out.println(tiController.update(thingItem2).toString());

        System.out.println("------------ Delete added items ------------");

        System.out.println("Success: " + tiController.delete(thingItem1.getId(),
                thingItem2.getId(),
                thingItem3.getId(),
                thingItem4.getId()));

         */

    }
}
