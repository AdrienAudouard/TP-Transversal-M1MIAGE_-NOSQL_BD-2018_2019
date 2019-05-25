package com.miage.bigdata.mains;

import com.miage.bigdata.controllers.DocumentController;
import com.miage.bigdata.controllers.ItemController;
import com.miage.bigdata.models.document.ThingItem;

public class DocumentMain {

    public static void main(String args[]) {
        DocumentController documentController = new DocumentController();
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

    }
}
