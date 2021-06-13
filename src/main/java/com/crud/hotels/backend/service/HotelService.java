package com.crud.hotels.backend.service;

import com.crud.hotels.backend.domain.Hotel;
import com.crud.hotels.backend.dto.HotelDto;
import com.crud.hotels.backend.exception.EntityNotFoundException;
import com.crud.hotels.backend.repository.HotelRepository;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class HotelService {
    private HotelRepository hotelRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public HotelService(HotelRepository hotelRepository, ModelMapper modelMapper) {
        this.hotelRepository = hotelRepository;
        this.modelMapper = modelMapper;
    }

    private static boolean accept(Hotel hotel) {
        return hotel.getFreeRooms() > 0;
    }

    @Transactional(readOnly = true)
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
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
                    Stream.of("Krakowiak,Poland,Krakow,10,20",
                            "DetucheHotel,Germany,Berlin,1,21",
                            "Matrioszka,Ukraine,Kiev,22,33",
                            "Marriot,Poland,Warszawa,0,10")
                            .map(name -> {
                                        String[] split = name.split(",");
                                        Hotel hotel = new Hotel();
                                        hotel.setName(split[0]);
                                        hotel.setCountry(split[1]);
                                        hotel.setCity(split[2]);
                                        hotel.setFreeRooms(Integer.valueOf(split[3]));
                                        hotel.setTotalRooms(Integer.valueOf(split[4]));
                                        return hotel;
                                    }
                            ).collect(Collectors.toList())
            );
        }
    }
}
