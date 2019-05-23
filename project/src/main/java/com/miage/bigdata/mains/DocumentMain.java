package com.miage.bigdata.mains;

import com.miage.bigdata.controllers.ThingItemController;
import com.miage.bigdata.models.document.ThingItem;

public class DocumentMain {

    public static void main(String args[]) {
        ThingItem thingItem1 = new ThingItem("1", "test");
        ThingItem thingItem2 = new ThingItem("2", "test");
        ThingItem thingItem3 = new ThingItem("3", "test");
        ThingItem thingItem4 = new ThingItem("4", "test");

        ThingItemController thingItemController = new ThingItemController();

        System.out.println("------------ Print all items ------------");

        System.out.println(thingItemController.getThingObjectDao().readAll().toString());

        System.out.println("------------ Add items ------------");

        System.out.println(thingItemController.getThingObjectDao().create(thingItem1, thingItem2, thingItem3, thingItem4).toString());

        System.out.println("------------ Get item with id 3 ------------");

        System.out.println(thingItemController.getThingObjectDao().getByID("3").toString());

        System.out.println("------------ Update item with id 3 ------------");

        thingItem2.setName("Test 2");

        System.out.println(thingItemController.getThingObjectDao().update(thingItem2).toString());

        System.out.println("------------ Delete added items ------------");

        System.out.println(thingItemController.getThingObjectDao().delete(thingItem1.getId(),
                thingItem2.getId(),
                thingItem3.getId(),
                thingItem4.getId()));
    }
}
