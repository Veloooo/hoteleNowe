package com.crud.hotels.backend.service;

import com.crud.hotels.backend.domain.Reservation;
import com.crud.hotels.backend.domain.Room;
import com.crud.hotels.backend.domain.User;
import com.crud.hotels.backend.dto.ReservationDto;
import com.crud.hotels.backend.dto.UserDto;
import com.crud.hotels.backend.exception.EntityNotFoundException;
import com.crud.hotels.backend.repository.ReservationRepository;
import com.crud.hotels.backend.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private ReservationRepository reservationRepository;
    private UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    public Reservation getReservationById(Long id) {
        return reservationRepository.getReservationById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public List<ReservationDto> getAllReservations() {
        return reservationRepository.findAll().stream()
                .map(reservation -> modelMapper.map(reservation, ReservationDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public void createReservation(ReservationDto reservationDto) {
        Reservation reservation = new Reservation.Builder()
                .dateFrom(reservationDto.getDateFrom())
                .dateTo(reservationDto.getDateTo())
                .paid(false)
                .daysTotal(reservationDto.getDateTo().getDayOfYear() - reservationDto.getDateFrom().getDayOfYear())
                .priceTotal(getPrice(reservationDto.getDateFrom(), reservationDto.getDateTo(), reservationDto.getRoom().getId()))
                .room(modelMapper.map(reservationDto.getRoom(), Room.class))
                .build();
        User user = userRepository.findUserByLogin(reservationDto.getUser().getLogin());
        user.addReservation(reservation);
        userRepository.save(user);
    }

    @Transactional
    public void updateReservation(ReservationDto reservationDto) {
        Reservation reservation = reservationRepository.getReservationById(reservationDto.getId()).get();
        reservation.setPaid(reservationDto.getPaid());
        reservationRepository.save(reservation);
    }

    @Transactional
    public List<ReservationDto> getAllUserReservations(UserDto userDto) {
        return reservationRepository.findAllByUser(modelMapper.map(userDto, User.class)).stream()
                .map(res -> modelMapper.map(res, ReservationDto.class))
                .collect(Collectors.toList());
    }

    public List<ReservationDto> getAllUserReservationsByUsername(String username) {
        UserDto userDto = modelMapper.map(userRepository.findUserByLogin(username), UserDto.class);
        return getAllUserReservations(userDto);
    }


    // TODO -> tu trzeba wykorzystać tę strategię rezerwacji
    private Integer getPrice(LocalDate dateFrom, LocalDate dateTo, Long roomId) {

        // Sprawdź ile czasu jest do rozpoczęcia wynajmu (early booking, normal, lastMinute)

        // Sprawdź obłożenie pokoi w danym terminie w danym hotelu (empty hotel, normal hotel, busy hotel)

        // Zwróć cenę rezerwacji

        return 1;
    }

    @Transactional
    public Reservation editReservation(Long id, ReservationDto reservationDto) {
        Reservation reservation = getReservationById(id);
        reservation.setDateFrom(reservationDto.getDateFrom());
        reservation.setDateTo(reservationDto.getDateTo());
        reservation.setDaysTotal(reservationDto.getDaysTotal());
        reservation.setPriceTotal(reservationDto.getPriceTotal());
        reservation.setPaid(reservationDto.getPaid());

        return reservationRepository.save(reservation);
    }

    public void deleteReservation(Long reservationId) {
        try {
            reservationRepository.deleteById(reservationId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException();
        }
    }
}
