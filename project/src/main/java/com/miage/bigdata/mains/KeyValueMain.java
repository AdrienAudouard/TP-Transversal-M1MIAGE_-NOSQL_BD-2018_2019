package com.miage.bigdata.mains;

import com.miage.bigdata.controllers.item.ItemController;
import com.miage.bigdata.controllers.models.ModelController;
import com.miage.bigdata.models.keyvalue.FeedbackItem;

import java.util.ArrayList;
import java.util.Date;

public class KeyValueMain {

    public static void main(String args[]) {
        ModelController modelController = new ModelController();
        ItemController<FeedbackItem> feedbackItemController = modelController.getItemController(FeedbackItem.class);

        //region Create
        System.out.println("------------ Create items ------------");

        FeedbackItem fb1 = new FeedbackItem("product1", "user1", 4.5, "super");
        FeedbackItem fb2 = new FeedbackItem("product1", "user2", 1.0, "déçu");

        FeedbackItem fb3 = new FeedbackItem("product2", "user1", 0.5, "jamais reçu");
        FeedbackItem fb4 = new FeedbackItem("product2", "user2", 3.0, "ok sans plus");

        System.out.println(feedbackItemController.create(fb1, fb2, fb3, fb4));
        //endregion

        //region GetAll
        System.out.println("------------ Print all items ------------");

        ArrayList<FeedbackItem> readFeedbackItems = new ArrayList<>(feedbackItemController.readAll());
        System.out.println(readFeedbackItems.toString());

        //What were the roles of the lines below ?
        //feedbackItemController.deleteTable();
        //feedbackItemController.createTable();

        //endregion

        //region DeleteAll
        System.out.println("------------ Delete all items ------------");

        for (FeedbackItem itemToDelete : readFeedbackItems) {
            System.out.print(feedbackItemController.delete(itemToDelete.getFullKey()) + " ");
        }
        //endregion
    }
}
