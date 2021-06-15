package com.crud.hotels.backend.controller;

import com.crud.hotels.backend.dto.HotelDto;
import com.crud.hotels.backend.service.HotelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1/hotels")
public class HotelController {
    private final HotelService hotelService;
    private final ModelMapper modelMapper;

    @Autowired
    public HotelController(HotelService hotelService, ModelMapper modelMapper) {
        this.hotelService = hotelService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<HotelDto> getHotels() {
        return hotelService.getAllHotels();
    }

    @GetMapping(value = "/{hotelId}")
    public HotelDto getHotel(@PathVariable Long hotelId) {
        return modelMapper.map(hotelService.getHotelById(hotelId), HotelDto.class);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createHotel(@Valid @RequestBody HotelDto hotelDto) {
        hotelService.createHotel(hotelDto);
    }

    @PutMapping(path = "/{hotelId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public HotelDto editHotel(@Valid @RequestBody HotelDto hotelDto, @PathVariable Long hotelId) {
        return modelMapper.map(hotelService.editHotel(hotelId, hotelDto), HotelDto.class);
    }

    @DeleteMapping(path = "/{hotelId}")
    public void deleteHotel(@PathVariable Long hotelId) {
        hotelService.deleteHotel(hotelId);
    }

    @GetMapping(path = "/available")
    public List<HotelDto> getHotelsWithAvailableRooms() {
        return hotelService.getAllHotelsWithFreeRooms()
                .stream()
                .map(hotel -> modelMapper.map(hotel, HotelDto.class))
                .collect(Collectors.toList());
    }
/*
    @GetMapping(path = "/{hotelId}/available")
    public boolean checkIfHotelAvailable(@PathVariable Long hotelId) {
        return hotelService.checkIfHotelAvailable(hotelId);
    }


 */
    @GetMapping(path = "/owned/{user}")
    public List<HotelDto> getHotelsOwnedByUser(@PathVariable String user) {
        return hotelService.getHotelsOwnedByUser(user);
    }

    @GetMapping(path = "/owned/{user}")
    public List<HotelDto> getHotelsOwnedByUser(@PathVariable String user, @RequestParam String name, @RequestParam String city,
                                               @RequestParam String country) {
        return hotelService.getHotelsOwnedByUser(user, name, city, country);
    }
}
