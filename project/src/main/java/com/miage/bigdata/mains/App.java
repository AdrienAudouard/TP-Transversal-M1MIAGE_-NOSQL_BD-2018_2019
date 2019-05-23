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
        System.out.println("---[KEY VALUE][TodosItem]---");
        TodoItemController todoItemController = TodoItemController.getInstance();

        List<TodoItem> todoItems = todoItemController.getTodoItems();
        for (TodoItem todoItem : todoItems) {
            System.out.println("[TodosItem] getTodoItems : " + todoItem.toString());
        }

        TodoItem createTodoItem = todoItemController.createTodoItem(new TodoItem("Name", "Cat TEST", true));
        System.out.println("[TodosItem] createTodoItem : " + createTodoItem.toString());

        //TodoItem updateTodoItem = todoItemController.updateTodoItem(new TodoItem("Name 2", "Cat TEST 2", false));
        //System.out.println("[TodosItem] updateTodoItem : " + updateTodoItem.toString());

        boolean deleteTodoItem = todoItemController.deleteTodoItem(createTodoItem.getId());
        System.out.println("[TodosItem] deleteTodoItem : " + deleteTodoItem);
    }
}
