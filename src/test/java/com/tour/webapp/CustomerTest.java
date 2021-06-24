package com.tour.webapp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tour.webapp.controllers.CustomerController;
import com.tour.webapp.dao.CustomerRepo;
import com.tour.webapp.model.Customers;
import com.tour.webapp.services.CustomerService;
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

@WebMvcTest(CustomerController.class)
public class CustomerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    @MockBean
    CustomerService service;

    @MockBean
    CustomerRepo repo;

    Customers customer_1 = new Customers(1, "Tom","email","address");
    Customers customer_2 = new Customers(2,"Bob","email2","address2");
    Customers customer_3 = new Customers(3,"John","email3","address3");

    @Test
    public void listTest() throws Exception {
        List<Customers> records = new ArrayList<>(Arrays.asList(customer_1,customer_2));

        Mockito.when(service.getListCustomers()).thenReturn(records);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/customer")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].name", is("Bob")));
    }

    @Test
    public void addTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                .post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(customer_3)))
                .andExpect(status().isOk());
    }


    @Test
    public void updateTest() throws Exception {

        Mockito.when(service.getCustomer(customer_1.getId())).thenReturn(customer_1);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/customer/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(customer_1)))
                .andExpect(status().isOk());
    }


    @Test
    public void deleteTest() throws Exception {
        Mockito.when(service.getCustomer(customer_1.getId())).thenReturn(customer_1);

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/customer/{id}",1)
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
