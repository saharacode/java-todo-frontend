package de.neuefische.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.neuefische.backend.model.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper = new ObjectMapper();

    @DirtiesContext
    @Test
    void addTodo_returnAddedTodoAsJson() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/api/todo")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "description" : "testTodo",
                            "status" : "OPEN"
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                            {
                                "description" : "testTodo",
                                "status" : "OPEN"
                            }
                            """))
                .andExpect(jsonPath("$.id").isNotEmpty()); // check that there is an uuid which is not null
    }

    @DirtiesContext
    @Test
    void getAllTodos_returnListWithAllTodosAsJson() throws Exception{
        // first create an Todo for the list which afterwards can be returned
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "description" : "testTodo",
                            "status" : "OPEN"
                        }
                        """))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString(); // convert the content of MVCResult to a string
        Todo todo = objectMapper.readValue(content, Todo.class); // convert the content-string to an todo-object

        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                            [
                                {
                                    "description" : "testTodo",
                                    "status" : "OPEN"
                                }
                            ]
                            """))
                .andExpect(jsonPath("$[0].id").value(todo.getId())); // $[0].id points at the id-value in the first list-element

    }

    @DirtiesContext
    @Test
    void getTodoById_returnTodoForIdAsJson() throws Exception{
        // first create an Todo for the list which afterwards can be returned
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "description" : "testTodo",
                            "status" : "OPEN"
                        }
                        """))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString(); // convert the content of MVCResult to a string
        Todo todo = objectMapper.readValue(content, Todo.class); // convert the content-string to an todo-object

        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo/" + todo.getId()))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                            
                                {
                                    "description" : "testTodo",
                                    "status" : "OPEN"
                                }
                            
                            """))
        .andExpect(jsonPath("$.id").value(todo.getId()));

    }


    @DirtiesContext
    @Test
    void editStatus_returnChangedTodoForIdAsJson() throws Exception{
        // first create an Todo for the list which afterwards can be edited
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "description" : "testTodo",
                            "status" : "OPEN"
                        }
                        """))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString(); // convert the content of MVCResult to a string
        Todo todo = objectMapper.readValue(content, Todo.class); // convert the content-string to an todo-object

        mockMvc.perform(MockMvcRequestBuilders.put("/api/todo/" + todo.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "description" : "testTodo2",
                            "status" : "DONE"
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                            
                                {
                                    "description" : "testTodo2",
                                    "status" : "DONE"
                                }
                            
                            """))
                .andExpect(jsonPath("$.id").value(todo.getId()));

    }



    @DirtiesContext
    @Test
    void deleteTodo_returnStringTrueForSuccessfulDelete() throws Exception{
        // first create an Todo for the list which afterwards can be deleted
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "description" : "testTodo",
                            "status" : "OPEN"
                        }
                        """))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString(); // convert the content of MVCResult to a string
        Todo todo = objectMapper.readValue(content, Todo.class); // convert the content-string to an todo-object

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/todo/" + todo.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string("true")); // returns a string instead of a boolean??

    }

}