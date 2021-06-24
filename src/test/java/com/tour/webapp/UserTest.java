package com.tour.webapp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tour.webapp.controllers.UserController;
import com.tour.webapp.model.Users;
import com.tour.webapp.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @MockBean
    UserService service;



    Users user_1 = new Users(1, "userOne","emailOne","addressOne");
    Users user_2 = new Users(2,"userTwo","emailTwo","addressTwo");
    Users user_3 = new Users(3,"userThree","emailThree","addressThree");

    @Test
    public void listTest() throws Exception {
        List<Users> records = new ArrayList<>(Arrays.asList(user_1,user_2));

        Mockito.when(service.getListUsers()).thenReturn(records);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/user")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].name", is("userTwo")));
    }

    @Test
    public void addTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                .post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(user_3)))
                .andExpect(status().isOk());
    }


    @Test
    public void updateTest() throws Exception {

        Mockito.when(service.getUser(user_1.getId())).thenReturn(user_1);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/user/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(user_1)))
                .andExpect(status().isOk());
    }


    @Test
    public void deleteTest() throws Exception {
        Mockito.when(service.getUser(user_1.getId())).thenReturn(user_1);

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/user/{id}",1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    /**
     * Maps an Object into a JSON String. Uses a Jackson ObjectMapper.
     */
    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }


}
