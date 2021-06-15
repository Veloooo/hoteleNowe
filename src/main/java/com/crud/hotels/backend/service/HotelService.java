package com.crud.hotels.backend.service;

import com.crud.hotels.backend.domain.Hotel;
import com.crud.hotels.backend.domain.User;
import com.crud.hotels.backend.dto.HotelDto;
import com.crud.hotels.backend.exception.EntityNotFoundException;
import com.crud.hotels.backend.repository.HotelRepository;
import com.crud.hotels.backend.repository.UserRepository;
import com.neovisionaries.i18n.CountryCode;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelService {
    private HotelRepository hotelRepository;
    private UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public HotelService(HotelRepository hotelRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.hotelRepository = hotelRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional(readOnly = true)
    public List<HotelDto> getAllHotels() {
        return hotelRepository.findAll().stream()
                .map(hotel -> modelMapper.map(hotel, HotelDto.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Hotel getHotelById(Long hotelId) {
        return hotelRepository.findById(hotelId).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public List<HotelDto> getAllHotelsWithFreeRooms() {
        return hotelRepository.getAllHotelsWithFreeRooms().stream()
                .map(hotel -> modelMapper.map(hotel, HotelDto.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<HotelDto> getHotelsOwnedByUser(String login, String name, String city, String country) {
        return hotelRepository.findAllByOwner(userRepository.findUserByLogin(login)).stream()
                .filter(hotel ->
                        (name != null && hotel.getName().toLowerCase().contains(name.toLowerCase())) &&
                                (city != null && hotel.getCity().toLowerCase().contains(city.toLowerCase())) &&
                                (country != null && hotel.getCountry().toLowerCase().contains(country.toLowerCase())))
                .map(hotel -> modelMapper.map(hotel, HotelDto.class))
                .collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    public List<HotelDto> getHotelsOwnedByUser(String login) {
        return hotelRepository.findAllByOwner(userRepository.findUserByLogin(login)).stream()
                .map(hotel -> modelMapper.map(hotel, HotelDto.class))
                .collect(Collectors.toList());
    }

    /*
    @Transactional(readOnly = true)
    public boolean checkIfHotelAvailable(Long id, ) {
        return hotelRepository.getHotelById(id)
                .orElseThrow(EntityNotFoundException::new)
                .getFreeRooms() > 0;
    }

     */
    @Transactional
    public void createHotel(HotelDto hotelDto) {
        Hotel hotel = new Hotel.Builder()
                .hotelName(hotelDto.getName())
                .hotelCountry(hotelDto.getCountry())
                .hotelCity(hotelDto.getCity())
                .hotelCurrency(CountryCode.findByName(hotelDto.getCountry()).get(0).getCurrency().getCurrencyCode())
                .build();
        User user = userRepository.findUserByLogin(hotelDto.getOwner().getLogin());
        user.addHotel(hotel);
        userRepository.save(user);
    }

    @Transactional
    public Hotel editHotel(Long id, HotelDto hotelDto) {
        Hotel hotel = getHotelById(id);
        hotel.setName(hotelDto.getName());
        hotel.setCity(hotelDto.getCity());
        hotel.setCountry(hotelDto.getCountry());
        return hotelRepository.save(hotel);
    }

    public void deleteHotel(Long hotelId) {
        try {
            hotelRepository.deleteById(hotelId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException();
        }
    }
}
