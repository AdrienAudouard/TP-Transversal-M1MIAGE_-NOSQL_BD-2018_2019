package com.miage.bigdata.service;

import java.util.List;

import com.miage.bigdata.repository.TodoItemRepository;
import com.miage.bigdata.repository.Repository;
import com.miage.bigdata.entity.TodoItem;
import lombok.NonNull;

public class TodoItemService {
    public static TodoItemService getInstance() {
        if (todoItemService == null) {
            todoItemService = new TodoItemService(new TodoItemRepository());
        }
        return todoItemService;
    }

    private static TodoItemService todoItemService;

    private final Repository repository;

    TodoItemService(Repository repository) {
        this.repository = repository;
    }

    public TodoItem createTodoItem(@NonNull String name,
                                   @NonNull String category, boolean isComplete) {
        TodoItem todoItem = TodoItem.builder().name(name).category(category)
                .complete(isComplete).build();
        return repository.createTodoItem(todoItem);
    }

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
}
