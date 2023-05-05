package de.neuefische.backend.service;

import de.neuefische.backend.model.Todo;
import de.neuefische.backend.repository.TodoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepo todoRepo;
    private final GenerateUUID generateUUID;

    public Todo addTodo(Todo newTodo) {
        String uuid = generateUUID.generateUUID(); // generate uuid as input for the key in the map in repo
        Todo todoWithId = new Todo(uuid,newTodo.getDescription(), newTodo.getStatus()); // create new object which contains now also an uuid
        return todoRepo.addTodo(todoWithId);
    }

    public List<Todo> getAllTodos() {
        return todoRepo.getAllTodos();
    }
}
