package com.crud.hotels.backend.controller;

import com.crud.hotels.backend.domain.Hotel;
import com.crud.hotels.backend.service.HotelService;
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
    public void shouldGetHotel() throws Exception {
        Hotel hotel = new Hotel.Builder()
                .hotelName("free")
                .hotelCountry("country1")
                .hotelCity("city1")
                .hotelCurrency("PLN")
                .build();
        when(service.getHotelById(1L)).thenReturn(hotel);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/hotels/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
