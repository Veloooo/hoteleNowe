package com.crud.hotels.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ReservationDto {
    private Long id;




    public void abc(){
        LocalDate date1 = LocalDate.of(2020, 1 , 1);
        LocalDate date2 = LocalDate.of(2020, 1 , 5);
        LocalDate date3 = LocalDate.of(2020, 1 , 10);
        LocalDate date4 = LocalDate.of(2020, 1 , 15);

    }

}
