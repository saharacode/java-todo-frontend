package de.neuefische.backend.controller;

import de.neuefische.backend.model.Todo;
import de.neuefische.backend.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @PostMapping("/todo")
    public Todo addTodo(@RequestBody Todo newTodo){
        return todoService.addTodo(newTodo);
    }

    @GetMapping("/todo")
    public List<Todo> getAllTodos(){
        return todoService.getAllTodos();
    }

    @GetMapping("/todo/{id}")
    public Todo getTodoById(@PathVariable String id){
        return todoService.getTodoById(id);
    }

}
