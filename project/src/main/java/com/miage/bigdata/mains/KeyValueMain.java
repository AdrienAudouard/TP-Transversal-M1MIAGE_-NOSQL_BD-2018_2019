package com.miage.bigdata.mains;

import com.miage.bigdata.controllers.item.ItemController;
import com.miage.bigdata.controllers.models.ModelController;
import com.miage.bigdata.models.keyvalue.FeedbackItem;

import java.util.ArrayList;
import java.util.List;

public class KeyValueMain {
    private static ModelController modelController = new ModelController();
    private static ItemController<FeedbackItem> feedbackItemController = modelController.getItemController(FeedbackItem.class);

    public static void main(String args[]) {
        System.out.println(deleteAllFeedbacks() ? "Success" : "Failed");
        System.out.println(createExampleFeedbacks() ? "Success" : "Failed");
        getAllFeedbacks().forEach(System.out::println);
        System.out.println(updateExampleFeedbacks() ? "Success" : "Failed");
        getAllFeedbacks().forEach(System.out::println);
    }

    private static boolean createExampleFeedbacks() {
        System.out.println("------------ Create example feedback items ------------");

        try {
            FeedbackItem fb1 = new FeedbackItem("product1", "user1", 4.0, "super");
            FeedbackItem fb2 = new FeedbackItem("product1", "user2", 2.0, "déçu");

            FeedbackItem fb3 = new FeedbackItem("product2", "user1", 1.0, "jamais reçu");
            FeedbackItem fb4 = new FeedbackItem("product2", "user2", 3.0, "ok sans plus");

            feedbackItemController.create(fb1, fb2, fb3, fb4);
        }
        catch (Exception exception) {
            return false;
        }

        return true;
    }

    private static List<FeedbackItem> getAllFeedbacks() {
        System.out.println("------------ Get all feedback items ------------");

        ArrayList<FeedbackItem> readFeedbackItems = new ArrayList<>(feedbackItemController.readAll());

        return readFeedbackItems;
    }

    private static boolean updateExampleFeedbacks() {
        System.out.println("------------ Update example feedback items ------------");

        try {
            for (FeedbackItem feedbackToUpdate : feedbackItemController.readAll()) {
                double currentRating = feedbackToUpdate.getRating();
                feedbackToUpdate.setRating(currentRating < 4 ? ++currentRating : currentRating);

                String currentReview = feedbackToUpdate.getReview();
                feedbackToUpdate.setReview(currentReview + " (edited)");

                feedbackItemController.update(feedbackToUpdate);
            }
        }
        catch (Exception exception) {
            return false;
        }

        return true;
    }

    private static boolean deleteAllFeedbacks() {
        System.out.println("------------ Delete all feedback items ------------");

        try {
            //for (FeedbackItem feedbackToDelete : getAllFeedbacks()) {
            //  feedbackItemController.delete(feedbackToDelete.getFullKey();
            //}

            feedbackItemController.deleteTable();
            feedbackItemController.createTable();
        }
        catch (Exception exception) {
            return false;
        }

        return true;
    }
}
