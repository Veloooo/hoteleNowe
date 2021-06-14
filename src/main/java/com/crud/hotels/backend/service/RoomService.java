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
        return roomRepository.getAllByHotel_IdAndAvailableIsTrue(id);
    }
}
