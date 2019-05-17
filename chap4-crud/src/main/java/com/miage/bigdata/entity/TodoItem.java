package com.miage.bigdata.entity;


import lombok.*;

@Data
@Builder
public class TodoItem {
    private String category;
    private boolean complete;
    private String id;
    private String name;
}
