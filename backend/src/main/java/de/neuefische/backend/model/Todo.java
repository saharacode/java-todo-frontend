package de.neuefische.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
public class Todo {
    private String id;
    private String description;
    private TodoStatus status;

    // two constructors needed:
    public Todo(String id, String description, TodoStatus status) { // building the object with id
        this.id = id;
        this.description = description;
        this.status = status;
    }

    public Todo(String description, TodoStatus status) { // build the object with the input from the frontend
        this.description = description;
        this.status = status;
    }
}
