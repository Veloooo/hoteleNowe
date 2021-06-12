package com.crud.hotels.backend.service;

import com.crud.hotels.backend.domain.Room;
import com.crud.hotels.backend.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    private RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getAllRoomsForHotel(Long id) {
        return roomRepository.getAllByHotel_Id(id);
    }

    public List<Room> getAllAvailableRoomsForHotel(Long id) {
        return roomRepository.getAllByHotel_IdAndAvailableIsTrue(id);
    }
}
