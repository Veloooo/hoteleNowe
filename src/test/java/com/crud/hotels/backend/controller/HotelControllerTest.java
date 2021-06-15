package com.crud.hotels.backend.controller;

import com.crud.hotels.backend.domain.Hotel;
import com.crud.hotels.backend.service.HotelService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;

class HotelControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    HotelService service;

    @Test
    public void shouldGetHotel() {
        Assert.assertTrue(true);
    }
}
