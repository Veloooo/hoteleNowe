package com.crud.hotels.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
public class ReservationDto {
    private Long id;

    private LocalDate dateFrom;

    private LocalDate dateTo;

    private UserDto user;

    private HotelDto hotel;

    private RoomDto room;

}
