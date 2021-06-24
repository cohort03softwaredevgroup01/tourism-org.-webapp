package com.tour.webapp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tour.webapp.controllers.HotelController;
import com.tour.webapp.model.Hotels;
import com.tour.webapp.services.HotelService;
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

@WebMvcTest(HotelController.class)
public class HotelTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @MockBean
    HotelService service;



    Hotels hotel_1 = new Hotels(1, "hotelOne","locationOne");
    Hotels hotel_2 = new Hotels(2,"hotelTwo","locationTwo");
    Hotels hotel_3 = new Hotels(3,"hotelThree","locationThree");

    @Test
    public void listTest() throws Exception {
        List<Hotels> records = new ArrayList<>(Arrays.asList(hotel_1,hotel_2));

        Mockito.when(service.getListHotels()).thenReturn(records);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/hotels")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].name", is("hotelTwo")));
    }

    @Test
    public void addTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                .post("/hotels")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(hotel_3)))
                .andExpect(status().isOk());
    }


    @Test
    public void updateTest() throws Exception {

        Mockito.when(service.getHotel(hotel_1.getId())).thenReturn(hotel_1);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/hotels/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(hotel_1)))
                .andExpect(status().isOk());
    }


    @Test
    public void deleteTest() throws Exception {
        Mockito.when(service.getHotel(hotel_1.getId())).thenReturn(hotel_1);

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/hotels/{id}",1)
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
