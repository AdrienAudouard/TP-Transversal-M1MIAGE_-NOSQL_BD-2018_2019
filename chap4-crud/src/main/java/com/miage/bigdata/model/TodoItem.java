package com.miage.bigdata.model;


import lombok.*;

@Data
@Builder
public class TodoItem {
    private String category;
    private boolean complete;
    private String id;
    private String name;
}
