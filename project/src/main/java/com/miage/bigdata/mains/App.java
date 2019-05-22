package com.miage.bigdata.mains;

import com.miage.bigdata.controllers.TodoItemController;
import com.miage.bigdata.models.TodoItem;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        todoItemCRUD();
    }

    private static void todoItemCRUD() {
        TodoItemController todoItemController = TodoItemController.getInstance();
        List<TodoItem> todoItems = todoItemController.getTodoItems();

        for (TodoItem todoItem : todoItems) {
            System.out.println("[TodosItem] getTodoItems : " + todoItem.toString());
        }
    }
}
