package com.miage.bigdata;

import com.google.gson.Gson;
import com.miage.bigdata.service.TodoItemService;
import com.miage.bigdata.entity.TodoItem;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App 
{
    private static final Gson gson = new Gson();
    private static TodoItemService todoItemService = new TodoItemService();
    private static final String MESSAGE_ERROR_INVALID_METHOD = "{'error': 'Invalid method'}";
    private static String apiResponse = MESSAGE_ERROR_INVALID_METHOD;

    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
        //testCrudItem();
    }

    public static void testCrudItem() {
        TodoItem johnDoe = TodoItem.builder().name("John Doe").category("Category 1")
                .complete(false).build();

        TodoItem jeanValjean = TodoItem.builder().name("Jean Valjean").category("Category 2")
                .complete(true).build();

        apiResponse = gson.toJson(todoItemService.createTodoItem(johnDoe));
        System.out.println( "Create Todos 1: "+ apiResponse);

        //apiResponse = gson.toJson(todoItemService.createTodoItem(jeanValjean));
        //System.out.println( "Create Todos 2: "+ apiResponse);

        //apiResponse = gson.toJson(todoItemService.getTodoItems());
        //System.out.println( "Todos collection: "+ apiResponse);

        //apiResponse = gson.toJson(todoItemService.getTodoItemById("d2658cbd-77d4-4c58-8781-c131efc63a01"));
        //System.out.println( "Todo by ID: "+ apiResponse);

        //apiResponse = gson.toJson(todoItemService.deleteTodoItem("d2658cbd-77d4-4c58-8781-c131efc63a01"));
        //System.out.println( "Remove Todo by ID: "+ apiResponse);
    }
}
