package com.miage.bigdata.mains;

import com.miage.bigdata.daos.loader.CsvLoader;
import com.miage.bigdata.daos.loader.JsonLoader;
import com.miage.bigdata.models.document.OrderItem;
import com.miage.bigdata.models.keyvalue.FeedbackItem;

import java.util.List;

public class Main {

    public static void main(String args[]) {
        JsonLoader jsonLoader = new JsonLoader();
        List orderItems = jsonLoader.load(OrderItem[].class, new OrderItem().getPathFileData());
        for (Object orderItem : orderItems) {
            System.out.println("[JSON] order" + orderItem);
        }

        /*CsvLoader csvLoader = new CsvLoader();
        List feedbackItems = csvLoader.load(FeedbackItem.class, new FeedbackItem().getPathFileData());
        for (Object feedbackItem : feedbackItems) {
            System.out.println("[CSV] feedback" + feedbackItem);
        }*/
    }
}
