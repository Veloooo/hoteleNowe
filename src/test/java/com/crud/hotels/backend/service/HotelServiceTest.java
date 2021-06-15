package com.crud.hotels.backend.service;

import com.crud.hotels.backend.domain.Hotel;
import com.crud.hotels.backend.domain.User;
import com.crud.hotels.backend.dto.HotelDto;
import com.crud.hotels.backend.exception.EntityNotFoundException;
import com.crud.hotels.backend.repository.HotelRepository;
import com.crud.hotels.backend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class HotelServiceTest {
    @Mock
    private ModelMapper modelMapper;

    @Mock
    private HotelRepository hotelRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private HotelService hotelService;

    @Test
    public void getAllHotelsShouldReturnEmptyListWhenNoHotelsInDb() {
        given(hotelRepository.findAll()).willReturn(Collections.emptyList());

        List<HotelDto> hotels = hotelService.getAllHotels();

        assertTrue(hotels.isEmpty());
    }

    @Test
    public void getAllHotelsShouldCorrectlyMapListOfHotelsToListOfHotelsDto() {
        given(hotelRepository.findAll()).willReturn(Arrays.asList(new Hotel(), new Hotel()));

        List<HotelDto> hotels = hotelService.getAllHotels();

        assertTrue(2 == hotels.size());
    }

    @Test
    public void getHotelByIdShouldThrowExceptionWhenNoHotelInDb() {
        given(hotelRepository.findById(any())).willReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> hotelService.getHotelById(2L));
    }

    @Test
    public void getAllHotelsWithFreeRoomsShouldReturnOnlyHotelsWithFreeRooms() {
        Hotel hotel1 = new Hotel.Builder()
                .hotelName("free")
                .hotelCountry("country1")
                .hotelCity("city1")
                .build();

        Hotel hotel2 = new Hotel.Builder()
                .hotelName("free")
                .hotelCountry("country2")
                .hotelCity("city2")
                .build();

        given(hotelRepository.getAllHotelsWithFreeRooms()).willReturn(Arrays.asList(hotel1, hotel2));

        List<HotelDto> hotels = hotelService.getAllHotelsWithFreeRooms();

        assertTrue(2 == hotels.size());
    }

    @Test
    public void getHotelsOwnedByUserShouldCorrectlyFilter() {
        Hotel hotel1 = new Hotel.Builder()
                .hotelName("free")
                .hotelCountry("country1")
                .hotelCity("city1")
                .build();

        Hotel hotel2 = new Hotel.Builder()
                .hotelName("free2")
                .hotelCountry("country2")
                .hotelCity("city2")
                .build();

        given(userRepository.findUserByLogin(any())).willReturn(new User());
        given(hotelRepository.findAllByOwner(any())).willReturn(Arrays.asList(hotel1, hotel2));

        List<HotelDto> hotels = hotelService.getHotelsOwnedByUser("user", "free", "city1", "country1");

        assertTrue(1 == hotels.size());
    }

    @Test
    public void checkIfHotelAvailableShouldThrowExceptionWhenNoHotelInDb() {
        given(hotelRepository.getHotelById(any())).willReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> hotelService.checkIfHotelAvailable(2L));
    }
}
