package com.miage.bigdata.mains;

import com.miage.bigdata.daos.loader.CsvLoader;
import com.miage.bigdata.daos.loader.JsonLoader;
import com.miage.bigdata.models.document.OrderItem;
import com.miage.bigdata.models.keyvalue.FeedbackItem;

import java.lang.reflect.Field;
import java.util.List;

public class Main {

    public static void main(String args[]) {
        JsonLoader jsonLoader = new JsonLoader();
        List orderItems = jsonLoader.load(OrderItem[].class);
        //System.out.println("[JSON] order" + orderItems);


        for (Field declaredField : FeedbackItem.class.getDeclaredFields()) {
            System.out.println("ATTRIBUTES: "+ declaredField.toString());
        }
        CsvLoader csvLoader = new CsvLoader();
        List feedbackItems = csvLoader.load(FeedbackItem.class);
        /*for (Object feedbackItem : feedbackItems) {
            System.out.println("[CSV] feedback" + feedbackItem);
        }*/
    }
}
