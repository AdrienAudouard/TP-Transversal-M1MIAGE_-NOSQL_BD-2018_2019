package com.miage.bigdata;

import com.google.gson.Gson;
import com.miage.bigdata.controllers.TodoItemController;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final Gson gson = new Gson();
    private static TodoItemController todoItemController = TodoItemController.getInstance();
    private static final String MESSAGE_ERROR_INVALID_METHOD = "{'error': 'Invalid method'}";
    private static String apiResponse = MESSAGE_ERROR_INVALID_METHOD;

    public static void main( String[] args )
    {
        apiResponse = gson.toJson(todoItemController.getTodoItems());
        System.out.println( "Todos collection: "+ apiResponse);
    }
}
