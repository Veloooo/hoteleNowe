package com.crud.hotels.backend.dto;

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
    private String login;

    @NotNull
    private String password;

    @NotNull
    private List<ReservationDto> reservations;
}
