package com.crud.hotels.backend.controller;

import com.crud.hotels.backend.dto.ReservationDto;
import com.crud.hotels.backend.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1/hotels/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping(path = "/{id}")
    public ReservationDto getReservation(@PathVariable Long reservationId) {
        /**
         * Zwrócenie rezerwacji
         */
       return null;
    }

    @GetMapping(path = "/")
    public List<ReservationDto> getReservations() {
        /**
         * Zwrócenie wszystkich rezerwacji ??????????????????????????????????????
         */
        return new ArrayList<>();
    }

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createReservation(@RequestBody ReservationDto reservationDto) {
       return;
    }


    @PutMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ReservationDto editReservation(@RequestBody ReservationDto reservationDto) {
        return null;
    }


    @DeleteMapping(path = "/")
    public void deleteReservation(@PathVariable Long reservationId) {
        return;
    }

    /**
     * ?????????????????????????????????????????
     * GET
     * /hotels/reservations/{id}/share - wysłanie rezerwacji na ustalony host i port
     * To bedzie oddzielny kontroler
     */

}
