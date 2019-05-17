package com.miage.bigdata.controller;

import com.miage.bigdata.entity.TodoItem;
import com.miage.bigdata.service.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value="/todos")
public class TodoItemController {

    @Autowired
    private TodoItemService service;

    @GetMapping("/example")
    public String example() {
        return "Hello User !! " + new Date();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<TodoItem>> getTodoItems() {
        List<TodoItem> body = service.getTodoItems();
        return new ResponseEntity<>(body, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value ="/{id:.*}", method = RequestMethod.GET)
    public ResponseEntity<TodoItem> getTodoItemById(@PathVariable(value="id") String id) {
        TodoItem body = service.getTodoItemById(id);
        return new ResponseEntity<>(body, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value ="/{id:.*}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteTodoItem(@PathVariable(value="id") String id) {
        service.deleteTodoItem(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createTodoItem(@RequestBody TodoItem todoItem) {
        service.createTodoItem(todoItem);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> updateTodoItem(@RequestBody TodoItem todoItem) {
        service.updateTodoItem(todoItem);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
