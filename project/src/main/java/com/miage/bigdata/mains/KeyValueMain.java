package com.miage.bigdata.mains;

import com.miage.bigdata.controllers.ItemController;
import com.miage.bigdata.controllers.KeyValueController;
import com.miage.bigdata.models.keyvalue.FeedbackItem;

import java.util.Date;

public class KeyValueMain {

    public static void main(String args[]) {
        KeyValueController keyValueController = new KeyValueController();
        ItemController<FeedbackItem> feedbackItemController = keyValueController.getItemController(FeedbackItem.class);

        //region Create
        System.out.println("------------ Create items ------------");

        FeedbackItem fb1 = new FeedbackItem("partition1", "row1", new Date());
        FeedbackItem fb2 = new FeedbackItem("partition1", "row2", new Date());

        FeedbackItem fb3 = new FeedbackItem("partition2", "row1", new Date());
        FeedbackItem fb4 = new FeedbackItem("partition2", "row2", new Date());

        System.out.println(feedbackItemController.create(fb1, fb2, fb3, fb4));
        //endregion

        //region GetAll
        System.out.println("------------ Print all items ------------");

        System.out.println(feedbackItemController.readAll().toString());

        feedbackItemController.deleteTable();

        feedbackItemController.createTable();
        //endregion

        //region DeleteAll
        System.out.println("------------ Delete all items ------------");

        System.out.println(feedbackItemController.delete());
        //endregion
    }
}
