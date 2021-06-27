package com.tour.webapp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tour.webapp.controllers.PackageController;
import com.tour.webapp.model.Packages;
import com.tour.webapp.services.PackageService;
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

@WebMvcTest(PackageController.class)
public class PackageTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @MockBean
    PackageService service;



    Packages package_1 = new Packages(1, "packageOne",500);
    Packages package_2 = new Packages(2,"packageTwo",1000);
    Packages package_3 = new Packages(3,"packageThree",1500);

    @Test
    public void listTest() throws Exception {
        List<Packages> records = new ArrayList<>(Arrays.asList(package_1,package_2));

        Mockito.when(service.getListPackages()).thenReturn(records);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/packages")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].name", is("packageTwo")));
    }

    @Test
    public void addTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                .post("/packages")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(package_3)))
                .andExpect(status().isOk());
    }


    @Test
    public void updateTest() throws Exception {

        Mockito.when(service.getPackage(package_1.getId())).thenReturn(package_1);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/packages/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(package_1)))
                .andExpect(status().isOk());
    }


    @Test
    public void deleteTest() throws Exception {
        Mockito.when(service.getPackage(package_1.getId())).thenReturn(package_1);

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/packages/{id}",1)
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
