package com.crud.hotels.backend.dto;

import com.crud.hotels.backend.domain.Room;
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
    private String currency;

    @NotNull
    private List<RoomDto> rooms;

    @NotNull
    private UserDto owner;

}
