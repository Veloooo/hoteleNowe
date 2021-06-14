package com.crud.hotels.backend.service;

import com.crud.hotels.backend.domain.Hotel;
import com.crud.hotels.backend.domain.Room;
import com.crud.hotels.backend.dto.HotelDto;
import com.crud.hotels.backend.dto.RoomDto;
import com.crud.hotels.backend.exception.EntityNotFoundException;
import com.crud.hotels.backend.repository.RoomRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RoomService(RoomRepository roomRepository, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.roomRepository = roomRepository;
    }

    public List<Room> getAllRoomsForHotel(Long id) {
        return roomRepository.getAllByHotel_Id(id);
    }

    public List<Room> getAllAvailableRoomsForHotel(Long id) {
        //return roomRepository.getAllByHotel_IdAndAvailableIsTrue(id);
        return null;
    }


    public void deleteRoom(Long id) {
        try {
            roomRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException();
        }
    }

    public void createRoom(RoomDto roomDto) {
        roomRepository.save(new Room.Builder()
                .name(roomDto.getName())
                .guestsNumber(roomDto.getGuestsNumber())
                .hotel(modelMapper.map(roomDto.getHotel(), Hotel.class))
                .pricePerNight(roomDto.getPricePerNight())
                .build());
    }

    public Room getRoomById(Long id) {
        return roomRepository.getOne(id);
    }

    @Transactional
    public Room editRoom(Long id, RoomDto roomDto) {
        Room room = getRoomById(id);
        room.setName(roomDto.getName());
        room.setPricePerNight(roomDto.getPricePerNight());
        room.setGuestsNumber(roomDto.getGuestsNumber());
        return roomRepository.save(room);
    }

    @Transactional(readOnly = true)
    public List<RoomDto> findAll(){
        return roomRepository.findAll()
                .stream()
                .map(room -> modelMapper.map(room, RoomDto.class))
                .collect(Collectors.toList());
    }

    /*
        TODO :: dodać filtrowanie po tych wartościach co są nieużywane
     */

    @Transactional(readOnly = true)
    public List<RoomDto> findAllWithCriteria(String name, LocalDate dateFromValue, LocalDate dateToValue, Double guestsNumberValue, Double pricePerNightValue, Double tempMinValue, HotelDto hotelValue){
        return roomRepository.findAll()
                .stream()
                //.filter()
                .map(room -> modelMapper.map(room, RoomDto.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<RoomDto> getAllRoomsInHotelWithCriteria(Long hotelId, String name, Double guestsNumberValue, Double pricePerNightValue, Double tempMinValue) {
        return roomRepository.getAllByHotel_Id(hotelId)
                .stream()
                .map(hotel -> modelMapper.map(hotel, RoomDto.class))
                .collect(Collectors.toList());
    }

}
