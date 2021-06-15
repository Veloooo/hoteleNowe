package com.crud.hotels.backend.controller;

import com.crud.hotels.backend.dto.RoomDto;
import com.crud.hotels.backend.service.RoomService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1/hotels/{hotelId}/rooms")
public class RoomController {
    private RoomService roomService;

    private final ModelMapper modelMapper;

    @Autowired
    public RoomController(RoomService roomService, ModelMapper modelMapper) {
        this.roomService = roomService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<RoomDto> getAllRoomsForHotel(@PathVariable Long hotelId) {
        return roomService.getAllRoomsForHotel(hotelId)
                .stream()
                .map(room -> modelMapper.map(room, RoomDto.class))
                .collect(Collectors.toList());
    }


}
