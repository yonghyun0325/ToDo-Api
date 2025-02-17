package com.example.todo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoDto {
    private String title;
    private String description;
    private boolean completed;
}
