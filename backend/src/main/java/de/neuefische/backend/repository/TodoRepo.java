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
        todoMap.replace(id,changedTodo); // exchange values, that status and name of todo can change
        return todoMap.get(id);
    }

    public boolean deleteTodo(String id) {
        boolean deleteStatus = false;
        todoMap.remove(id);
        if (todoMap.isEmpty()){ // change status when the delete was successful
            deleteStatus = true;
        }
        return deleteStatus;
    }
}
