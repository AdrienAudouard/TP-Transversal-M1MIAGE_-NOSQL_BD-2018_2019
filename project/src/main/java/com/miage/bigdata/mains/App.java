package com.miage.bigdata.mains;

import com.miage.bigdata.controllers.Controller;
import com.miage.bigdata.daos.itemDao.ThingDaoFactory;
import com.miage.bigdata.daos.itemDao.TodoItemDaoFactory;
import com.miage.bigdata.models.Thing;
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
        thingsCRUD();
    }

    private static void todoItemCRUD() {
        System.out.println("---[KEY VALUE][TodosItem]---");
        Controller todoItemController = new Controller<TodoItem>(TodoItemDaoFactory.getDao());

        List<TodoItem> todoItems = todoItemController.readAll();
        for (TodoItem todoItem : todoItems) {
            System.out.println("[TodosItem] readAll : " + todoItem.toString());
        }

        Object createTodoItem = todoItemController.create(new TodoItem("Name", "Cat TEST", true));
        TodoItem newTodoItem = (TodoItem) createTodoItem;
        System.out.println("[TodosItem] create : " + newTodoItem.toString());

        //TodoItem updateTodoItem = todoItemController.update(new TodoItem("Name 2", "Cat TEST 2", false));
        //System.out.println("[TodosItem] update : " + updateTodoItem.toString());

        boolean deleteTodoItem = todoItemController.delete(newTodoItem.getId());
        System.out.println("[TodosItem] delete : " + deleteTodoItem);
    }

    private static void thingsCRUD() {
        System.out.println("---[DOCUMENT][Things]---");
        Controller thingController = new Controller<Thing>(ThingDaoFactory.getDao());

        List<Thing> things = thingController.readAll();
        for (Thing thing : things) {
            System.out.println("[Thing] readAll : " + thing.toString());
        }

    }
}
