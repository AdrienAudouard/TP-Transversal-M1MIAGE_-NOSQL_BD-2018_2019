package com.miage.bigdata.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoItem {
    private String category;
    private boolean complete;
    private String id;
    private String name;
}
