package com.miage.bigdata.mains;

import com.miage.bigdata.controllers.ThingItemController;
import com.miage.bigdata.daos.itemDao.document.ThingObjectDao;
import com.miage.bigdata.models.document.ThingItem;

public class DocumentMain {

    public static void main(String args[]) {
        ThingItemController thingItemController = new ThingItemController();
        ThingObjectDao thingObjectDao = thingItemController.getThingObjectDao();

        System.out.println("------------ Delete table ------------");

        System.out.println("Success: " + thingObjectDao.deleteTable());

        System.out.println("------------ Create table ------------");

        System.out.println("Success: " + thingObjectDao.createTable());


        ThingItem thingItem1 = new ThingItem(thingObjectDao.generateID(), "test");
        ThingItem thingItem2 = new ThingItem(thingObjectDao.generateID(), "test");
        ThingItem thingItem3 = new ThingItem(thingObjectDao.generateID(), "test");
        ThingItem thingItem4 = new ThingItem(thingObjectDao.generateID(), "test");


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
