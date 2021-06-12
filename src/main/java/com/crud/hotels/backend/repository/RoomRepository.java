package com.crud.hotels.backend.repository;

import com.crud.hotels.backend.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> getAllByHotel_Id(Long id);

    List<Room> getAllByHotel_IdAndAvailableIsTrue(Long id);
}
