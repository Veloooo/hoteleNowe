package com.crud.hotels.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
public class ReservationDto {
    private Long id;

    private LocalDate createDate;

    private LocalDate dateFrom;

    private LocalDate dateTo;

    private UserDto user;

    private RoomDto room;

    private Integer daysTotal;

    private Integer priceTotal;

    private Boolean paid;

}
