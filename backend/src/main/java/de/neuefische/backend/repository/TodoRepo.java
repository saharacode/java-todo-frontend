package de.neuefische.backend.repository;

import de.neuefische.backend.model.Todo;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@RequiredArgsConstructor
@Repository
public class TodoRepo {
    private final Map<String,Todo> todoMap;

    public Todo addTodo(Todo todoWithId) {
        todoMap.put(todoWithId.getId(), todoWithId); // use the uuid to put it as the key in the map
        return todoMap.get(todoWithId.getId());
    }

    public List<Todo> getAllTodos() {
        List<Todo> todoList= new ArrayList<>(); // get every todo and put it into a list.
        for (String mapKey: todoMap.keySet()) {
            todoList.add(todoMap.get(mapKey));
        }
        return todoList;
    }

    public Todo getTodoById(String id) {
        return todoMap.get(id); // return the Todo with the requested id
    }

    public Todo editStatus(String id, Todo changedTodo) {
        Todo todo = todoMap.get(id); // get the todo with the requested id
        todo.setStatus(changedTodo.getStatus()); // edit the status to the requested status
        return todo;
    }
}
