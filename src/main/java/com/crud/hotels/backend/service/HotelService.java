package com.crud.hotels.backend.service;

import com.crud.hotels.backend.domain.Hotel;
import com.crud.hotels.backend.domain.User;
import com.crud.hotels.backend.dto.HotelDto;
import com.crud.hotels.backend.exception.EntityNotFoundException;
import com.crud.hotels.backend.repository.HotelRepository;
import com.crud.hotels.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    private static boolean accept(Hotel hotel) {
        return hotel.getFreeRooms() > 0;
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

    @Transactional(readOnly = true)
    public List<HotelDto> getAllHotelsWithFreeRooms(String filterText) {
        List<Hotel> hotels;
        if (filterText == null || filterText.isEmpty()) {
            hotels = hotelRepository.getAllHotelsWithFreeRooms();
        } else {
            hotels = hotelRepository.findAllByNameContaining(filterText);
        }
        return hotels.stream()
                .map(hotel -> modelMapper.map(hotel, HotelDto.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<HotelDto> getAllHotelsWithFreeRooms(String filterText, LocalDate dateFrom, LocalDate dateTo) {
        List<Hotel> hotels;
        if (filterText == null || filterText.isEmpty()) {
            hotels = hotelRepository.getAllHotelsWithFreeRooms();
        } else {
            hotels = hotelRepository.findAllByNameContaining(filterText);
        }
        return hotels.stream()
                .map(hotel -> modelMapper.map(hotel, HotelDto.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public boolean checkIfHotelAvailable(Long id) {
        return hotelRepository.getHotelById(id)
                .orElseThrow(EntityNotFoundException::new)
                .getFreeRooms() > 0;
    }

    @Transactional
    public void createHotel(HotelDto hotelDto) {
        Hotel hotel = new Hotel(hotelDto.getName(),
                hotelDto.getCountry(),
                hotelDto.getCity(),
                hotelDto.getTotalRooms(),
                hotelDto.getTotalRooms());
        hotelRepository.save(hotel);
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

    @PostConstruct
    public void insertSampleData() {
        if (hotelRepository.count() < 5) {
            hotelRepository.saveAll(
                    Stream.of("Krakowiak,Poland,Krakow,10,20,Bogdan",
                            "DetucheHotel,Germany,Berlin,1,21,Bogdan",
                            "Matrioszka,Ukraine,Kiev,22,33,Janusz",
                            "Marriot,Poland,Warszawa,0,10,Janusz")
                            .map(name -> {
                                        String[] split = name.split(",");
                                        Hotel hotel = new Hotel();
                                        hotel.setName(split[0]);
                                        hotel.setCountry(split[1]);
                                        hotel.setCity(split[2]);
                                        hotel.setFreeRooms(Integer.valueOf(split[3]));
                                        hotel.setTotalRooms(Integer.valueOf(split[4]));
                                        User user = userRepository.findUserByLogin(split[5]);
                                        user.addHotel(hotel);
                                        return hotel;
                                    }
                            ).collect(Collectors.toList())
            );
        }
    }
}
