package com.crud.hotels.backend.controller;

import com.crud.hotels.backend.dto.RoomDto;
import com.crud.hotels.backend.service.RoomService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1/rooms")
public class RoomController {
    private RoomService roomService;

    private final ModelMapper modelMapper;

    @Autowired
    public RoomController(RoomService roomService, ModelMapper modelMapper) {
        this.roomService = roomService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<RoomDto> getAllRoomsForHotel(@RequestParam Long hotelId) {
        return roomService.getAllRoomsForHotel(hotelId)
                .stream()
                .map(room -> modelMapper.map(room, RoomDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping
    public List<RoomDto> getAllRooms() {
        return roomService.findAll();
    }

    @GetMapping(value = "/{id}")
    public RoomDto getRoom(@PathVariable Long id) {
        return modelMapper.map(roomService.getRoomById(id), RoomDto.class);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createRoom(@Valid @RequestBody RoomDto roomDto) {
        roomService.createRoom(roomDto);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public RoomDto editRoom(@Valid @RequestBody RoomDto roomDto, @PathVariable Long id) {
        return modelMapper.map(roomService.editRoom(id, roomDto), RoomDto.class);
    }

    @GetMapping
    public List<RoomDto> getAllFreeRoomsBetweenDate(@RequestParam LocalDate dateFrom, @RequestParam LocalDate dateTo) {
        return roomService.findAllWithCriteria(dateFrom, dateTo);
    }

    @GetMapping
    public List<RoomDto> getAllFreeRoomsForSpecifiedNumberOfPeople(@RequestParam Double guests) {
        return roomService.findAllWithCriteria(guests);
    }


}
