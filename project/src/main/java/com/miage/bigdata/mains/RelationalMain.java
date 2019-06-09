package com.miage.bigdata.mains;

import com.miage.bigdata.controllers.ItemController;
import com.miage.bigdata.controllers.RelationalController;
import com.miage.bigdata.models.relational.TodoItem;

/**
 * Hello world!
 *
 */
public class RelationalMain {
    public static void main( String[] args ) {
        RelationalController relationalController = new RelationalController();
        ItemController<TodoItem> tdiController = relationalController.getItemController(TodoItem.class);

        System.out.println("------------ Print all items ------------");

        System.out.println(tdiController.readAll().toString());

        TodoItem item = new TodoItem("Test", false, "2", "lkjhj");
        TodoItem item4 = new TodoItem("Test", false, "3", "lkjhj");
        TodoItem item2 = new TodoItem("Test", false, "4", "lkjhj");
        TodoItem item3 = new TodoItem("Test", false, "5", "lkjhj");
        TodoItem item5 = new TodoItem("Test", false, "1", "lkjhj");

        System.out.println("------------ Create items ------------");

        System.out.println(tdiController.create(item, item2, item3, item4, item5).toString());

        System.out.println("------------ Print all items ------------");

        System.out.println(tdiController.readAll().toString());

        System.out.println("------------ Get item with id 2 ------------");

        System.out.println(tdiController.getByID("2").toString());

        System.out.println("------------ Update item with id 3 ------------");

        item4.setCategory("Test 2");

        System.out.println(tdiController.update(item4).toString());

        System.out.println("------------ Delete all items ------------");

        System.out.println("Success: " + tdiController.delete(
                item.getId(),
                item2.getId(),
                item3.getId(),
                item4.getId(),
                item5.getId()
                )
        );
    }
}
