package com.crud.hotels.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDto {
    private Long id;

    private LocalDate dateFrom;

    private LocalDate dateTo;

    private UserDto user;

    private HotelDto hotel;

    private RoomDto room;

}
