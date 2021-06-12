package com.crud.hotels.backend.dto;

import com.crud.hotels.backend.domain.Reservation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HotelDto {
    private String id;

    @NotNull
    private String name;

    @NotNull
    private String country;

    @NotNull
    private String city;

    @NotNull
    private Integer totalRooms;

    private Integer freeRooms;

    private List<Reservation> reservations;
}
