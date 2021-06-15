package com.crud.hotels.backend.controller;

import com.crud.hotels.backend.dto.ReservationDto;
import com.crud.hotels.backend.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1/hotels/reservations")
public class ReservationController {

    private final ReservationService reservationService;
    private final ModelMapper modelMapper;

    @Autowired
    public ReservationController(ReservationService reservationService, ModelMapper modelMapper) {
        this.reservationService = reservationService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(path = "/{reservationId}")
    public ReservationDto getAllReservations(@PathVariable Long reservationId) {
        return modelMapper.map(reservationService.getReservationById(reservationId), ReservationDto.class);
    }

    @GetMapping(path = "/{user}")
    public List<ReservationDto> getAllUserReservations(@PathVariable String user) {
        return reservationService.getAllUserReservationsByUsername(user);
    }

    @GetMapping
    public List<ReservationDto> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createReservation(@RequestBody ReservationDto reservationDto) {
       reservationService.createReservation(reservationDto);
    }


    @PutMapping(path = "/{reservationId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ReservationDto editReservation(@PathVariable Long reservationId, @Valid @RequestBody ReservationDto reservationDto) {
        return modelMapper.map(reservationService.editReservation(reservationId, reservationDto), ReservationDto.class);
    }


    @DeleteMapping(path = "/{reservationId}")
    public void deleteReservation(@PathVariable Long reservationId) {
        reservationService.deleteReservation(reservationId);
    }

}
