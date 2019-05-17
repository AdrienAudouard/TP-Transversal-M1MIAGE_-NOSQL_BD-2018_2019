package com.miage.bigdata.service;

import java.util.List;

import com.miage.bigdata.repository.TodoItemRepository;
import com.miage.bigdata.repository.Repository;
import com.miage.bigdata.entity.TodoItem;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoItemService {

    @Autowired
    private TodoItemRepository repository;

    public TodoItem createTodoItem(@NonNull TodoItem todoItem) {
        return repository.createTodoItem(todoItem);
    }

    public boolean deleteTodoItem(@NonNull String id) {
        return repository.deleteTodoItem(id);
    }

    public TodoItem getTodoItemById(@NonNull String id) {
        return repository.readTodoItem(id);
    }

    public List<TodoItem> getTodoItems() {
        return repository.readTodoItems();
    }

    public TodoItem updateTodoItem(@NonNull String id, boolean isComplete) {
        return repository.updateTodoItem(id, isComplete);
    }

    public TodoItem updateTodoItem(@NonNull TodoItem todoItem) {
        return repository.updateTodoItem(todoItem);
    }
}
