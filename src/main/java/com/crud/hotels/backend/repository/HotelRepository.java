package com.crud.hotels.backend.repository;

import com.crud.hotels.backend.domain.Hotel;
import com.crud.hotels.backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    @Query(value = "SELECT * FROM hotels WHERE free_rooms > 0", nativeQuery = true)
    List<Hotel> getAllHotelsWithFreeRooms();

    List<Hotel> findAllByNameContaining(String name);

    List<Hotel> findAllByOwner(User user);

    Optional<Hotel> getHotelById(Long id);
}
