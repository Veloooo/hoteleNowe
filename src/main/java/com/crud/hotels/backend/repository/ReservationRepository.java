package com.crud.hotels.backend.repository;

import com.crud.hotels.backend.domain.Reservation;
import com.crud.hotels.backend.domain.User;
import net.bytebuddy.utility.RandomString;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    List<Reservation> findAll();

    List<Reservation> findAllByUser(User user);

    Reservation getReservationById(Long id);

    Reservation save(Reservation task);

    void deleteById(Long taskId);

    void deleteAll();
}
