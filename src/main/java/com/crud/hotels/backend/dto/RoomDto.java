package com.crud.hotels.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomDto {

    private Long id;

    private String name;

    private Double guestsNumber;

    private Double pricePerNight;

    private Double pricePerNightInUserCurrency;

    private HotelDto hotel;

    private List<ReservationDto> reservations;
}
