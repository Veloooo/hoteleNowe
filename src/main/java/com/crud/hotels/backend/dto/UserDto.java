package com.crud.hotels.backend.dto;

import com.crud.hotels.backend.domain.Hotel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @NotNull
    private Long id;

    @NotNull
    private String login;

    @NotNull
    private String password;

    @NotNull
    private String role;

    @NotNull
    private List<ReservationDto> reservations;

    @NotNull
    private List<HotelDto> hotels;
}
