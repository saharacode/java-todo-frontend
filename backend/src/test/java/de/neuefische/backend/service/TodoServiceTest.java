package de.neuefische.backend.service;

import de.neuefische.backend.model.Todo;
import de.neuefische.backend.model.TodoStatus;
import de.neuefische.backend.repository.TodoRepo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TodoServiceTest {
    // create mocks
    TodoRepo todoRepo = mock(TodoRepo.class);
    GenerateUUID generateUUID = mock(GenerateUUID.class);

    // initialize TodoService
    TodoService todoService = new TodoService(todoRepo,generateUUID);

    @Test
    void addTodo_returnTodoWithID(){
        // given
        String description = "Test";
        TodoStatus newTodoStatus = TodoStatus.OPEN;
        Todo newTodo = new Todo(description,newTodoStatus);

        String uuid = "uuid";
        Todo expectedTodo = new Todo(uuid,description,newTodoStatus);

        // when
        when(generateUUID.generateUUID()).thenReturn(uuid);
        when(todoRepo.addTodo(expectedTodo)).thenReturn(expectedTodo);
        Todo actualTodo = todoService.addTodo(newTodo);

        // then
        assertEquals(expectedTodo,actualTodo);
        verify(todoRepo).addTodo(expectedTodo);
        verify(generateUUID).generateUUID();
    }

}