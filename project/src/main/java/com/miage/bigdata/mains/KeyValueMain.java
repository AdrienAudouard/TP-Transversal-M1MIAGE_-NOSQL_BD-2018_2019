package com.miage.bigdata.mains;

import com.miage.bigdata.controllers.item.ItemController;
import com.miage.bigdata.controllers.models.ModelController;
import com.miage.bigdata.models.keyvalue.FeedbackItem;
import com.miage.bigdata.models.keyvalue.KeyValueItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class KeyValueMain {
    private static ModelController modelController = new ModelController();
    private static ItemController<FeedbackItem> feedbackItemController = modelController.getItemController(FeedbackItem.class);

    public static void main(String args[]) {
        deleteAllFeedbacks();
        createExampleFeedbacks();
        getAllFeedbacks();
        updateExampleFeedbacks();
        getAllFeedbacks();
    }

    private static void createExampleFeedbacks() {
        System.out.println("------------ Create example feedback items ------------");

        FeedbackItem fb1 = new FeedbackItem("product1", "user1", 4.5, "super");
        FeedbackItem fb2 = new FeedbackItem("product1", "user2", 1.0, "déçu");

        FeedbackItem fb3 = new FeedbackItem("product2", "user1", 0.5, "jamais reçu");
        FeedbackItem fb4 = new FeedbackItem("product2", "user2", 3.0, "ok sans plus");

        System.out.println(feedbackItemController.create(fb1, fb2, fb3, fb4));
    }

    private static List<FeedbackItem> getAllFeedbacks() {
        System.out.println("------------ Get all feedback items ------------");

        ArrayList<FeedbackItem> readFeedbackItems = new ArrayList<>(feedbackItemController.readAll());
        System.out.println(readFeedbackItems.toString());

        return readFeedbackItems;
    }

    private static void updateExampleFeedbacks() {
        System.out.println("------------ Update example feedback items ------------");

        FeedbackItem fb1 = new FeedbackItem("product1", "user1", 5.0, "parfait depuis la réponse du support client");
        FeedbackItem fb2 = new FeedbackItem("product1", "user2", 3.0, "en fait j'aime bien");

        FeedbackItem fb3 = new FeedbackItem("product2", "user1", 3.0, "bien reçu finalement juste long");
        FeedbackItem fb4 = new FeedbackItem("product2", "user2", 1.0, "il s'est cassé au bout d'une semaine");

        System.out.println(feedbackItemController.update(fb1));
        System.out.println(feedbackItemController.update(fb2));
        System.out.println(feedbackItemController.update(fb3));
        System.out.println(feedbackItemController.update(fb4));
    }

    private static void deleteAllFeedbacks() {
        System.out.println("------------ Delete all feedback items ------------");

        for (FeedbackItem itemToDelete : getAllFeedbacks()) {
            System.out.print(feedbackItemController.delete(itemToDelete.getFullKey()) + " ");
        }
    }
}
