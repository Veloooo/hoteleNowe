package com.crud.hotels.backend.dto;

import com.crud.hotels.backend.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserReportDto {

    private Long id;

    private LocalDate reportDate;

    private Integer roomsRented;

    private User owner;
}
