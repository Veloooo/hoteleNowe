package com.crud.hotels.backend.service;

import com.crud.hotels.backend.domain.Hotel;
import com.crud.hotels.backend.domain.Reservation;
import com.crud.hotels.backend.domain.Room;
import com.crud.hotels.backend.domain.User;
import com.crud.hotels.backend.dto.HotelDto;
import com.crud.hotels.backend.dto.ReservationDto;
import com.crud.hotels.backend.exception.EntityNotFoundException;
import com.crud.hotels.backend.repository.ReservationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservationService {

    private ReservationRepository reservationRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, ModelMapper modelMapper) {
        this.reservationRepository = reservationRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public void createReservation(ReservationDto reservationDto) {
        Reservation reservation = new Reservation.Builder()
                .dateFrom(reservationDto.getDateFrom())
                .dateTo(reservationDto.getDateTo())
                .room(modelMapper.map(reservationDto.getRoom(), Room.class))
                .user(modelMapper.map(reservationDto.getUser(), User.class))
                .hotel(modelMapper.map(reservationDto.getHotel(), Hotel.class))
                .build();
        reservationRepository.save(reservation);
    }


    public void deleteReservation(Long reservationId) {
        try {
            reservationRepository.deleteById(reservationId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException();
        }
    }
}
