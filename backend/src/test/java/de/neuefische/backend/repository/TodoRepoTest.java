package de.neuefische.backend.repository;

import de.neuefische.backend.model.Todo;
import de.neuefische.backend.model.TodoStatus;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class TodoRepoTest {

    // create a global Test-Todo which can be used for all following tests
    String uuid = "uuid";
    String description = "Test";
    TodoStatus newTodoStatus = TodoStatus.OPEN;
    Todo newTodo = new Todo(uuid,description,newTodoStatus);



    @Test
    void addTodo_returnSameTodoAsInput(){
        // given
        // initialize TodoRepo
        Map<String,Todo> todoMap = new HashMap<>();
        TodoRepo todoRepo = new TodoRepo(todoMap);

        Todo expectedTodo = newTodo;

        // when
        Todo actualTodo = todoRepo.addTodo(newTodo);

        // then
        assertEquals(expectedTodo,actualTodo);


    }

    @Test
    void getAllTodos_returnListWithOneTodoInside(){
        // given
        // initialize TodoRepo with Map
        Map<String,Todo> todoMap = new HashMap<>();
        todoMap.put(newTodo.getId(), newTodo);
        TodoRepo todoRepo = new TodoRepo(todoMap);

        List<Todo> expectedList = Arrays.asList(newTodo);

        // when
        List<Todo> actualList = todoRepo.getAllTodos();

        // then
        assertEquals(expectedList,actualList);
    }

    @Test
    void getTodoById_returnSameTodoWhichWasAddedToMap(){
        // given
        // initialize TodoRepo with Map
        Map<String,Todo> todoMap = new HashMap<>();
        todoMap.put(newTodo.getId(), newTodo);
        TodoRepo todoRepo = new TodoRepo(todoMap);

        Todo expectedTodo = newTodo;

        // when
        Todo actualTodo = todoRepo.getTodoById(newTodo.getId());

        // then
        assertEquals(expectedTodo,actualTodo);
    }

    @Test
    void editStatus_returnTheEditedTodo(){
        // given
        // initialize TodoRepo with Map
        Map<String,Todo> todoMap = new HashMap<>();
        todoMap.put(newTodo.getId(), newTodo);
        TodoRepo todoRepo = new TodoRepo(todoMap);

        // create a second Todo with the same id
        Todo expectedTodo = new Todo("uuid","Test2",TodoStatus.IN_PROGRESS);

        // when
        Todo actualTodo = todoRepo.editStatus("uuid", expectedTodo); // Mapvalue for uuid should change form newTodo to editedTodo

        // then
        assertEquals(expectedTodo,actualTodo);
    }

    @Test
    void deleteTodo_returnDeleteStatusTrue(){
        // given
        // create a second Todo with different ID and status Done
        Todo secondTodo = new Todo("uuid2","Test2",TodoStatus.DONE);

        // initialize TodoRepo with Map
        Map<String,Todo> todoMap = new HashMap<>();
        todoMap.put(newTodo.getId(), newTodo);
        todoMap.put(secondTodo.getId(),secondTodo);
        TodoRepo todoRepo = new TodoRepo(todoMap); // map has two objects, one will be deleted

        // when
        boolean deleteStatus = todoRepo.deleteTodo("uuid2");

        // then
        assertTrue(deleteStatus);
    }



}