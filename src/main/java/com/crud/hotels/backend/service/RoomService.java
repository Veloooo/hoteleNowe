package com.crud.hotels.backend.service;

import com.crud.hotels.backend.domain.Hotel;
import com.crud.hotels.backend.domain.Room;
import com.crud.hotels.backend.dto.RoomDto;
import com.crud.hotels.backend.exception.EntityNotFoundException;
import com.crud.hotels.backend.repository.RoomRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final WeatherService weatherService;
    private final CurrencyService currencyService;
    private final ModelMapper modelMapper;



    @Autowired
    public RoomService(RoomRepository roomRepository, ModelMapper modelMapper, WeatherService weatherService, CurrencyService currencyService) {
        this.modelMapper = modelMapper;
        this.roomRepository = roomRepository;
        this.weatherService = weatherService;
        this.currencyService = currencyService;
    }

    public List<Room> getAllRoomsForHotel(Long id) {
        return roomRepository.getAllByHotel_Id(id);
    }



    public void deleteRoom(Long id) {
        try {
            roomRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException();
        }
    }

    public void createRoom(RoomDto roomDto) {
        roomRepository.save(new Room.Builder()
                .name(roomDto.getName())
                .guestsNumber(roomDto.getGuestsNumber())
                .hotel(modelMapper.map(roomDto.getHotel(), Hotel.class))
                .pricePerNight(roomDto.getPricePerNight())
                .build());
    }

    public Room getRoomById(Long id) {
        return roomRepository.getOne(id);
    }

    @Transactional
    public Room editRoom(Long id, RoomDto roomDto) {
        Room room = getRoomById(id);
        room.setName(roomDto.getName());
        room.setPricePerNight(roomDto.getPricePerNight());
        room.setGuestsNumber(roomDto.getGuestsNumber());
        return roomRepository.save(room);
    }

    @Transactional(readOnly = true)
    public List<RoomDto> findAll() {
        return roomRepository.findAll()
                .stream()
                .map(room -> modelMapper.map(room, RoomDto.class))
                .collect(Collectors.toList());
    }


    public List<RoomDto> findAllWithCriteria(LocalDate dateFrom, LocalDate dateTo) {
        return findAllWithCriteria(null, dateFrom, dateTo, null, null, null, null, null);
    }

    public List<RoomDto> findAllWithCriteria(Double guests) {
        return findAllWithCriteria(null, null, null, guests, null, null, null, null);
    }

    @Transactional(readOnly = true)
    public List<RoomDto> findAllWithCriteria(String name, LocalDate dateFromValue, LocalDate dateToValue, Double guestsNumberValue, Double pricePerNightValue, Double tempMinValue, String hotelName, String userLocale) {
        List<Room> rooms = roomRepository.findAll()
                .stream()
                .filter(room ->
                        room.getName().toLowerCase().contains(name.toLowerCase()) &&
                                reservationsMatch(modelMapper.map(room, RoomDto.class), dateFromValue, dateToValue) &&
                                guestsNumberMatch(room.getGuestsNumber(), guestsNumberValue) &&
                                priceMatch(room.getPricePerNight(), pricePerNightValue) &&
                                room.getHotel().getName().contains(hotelName) &&
                                tempMatch(tempMinValue, room.getHotel().getCountry(), room.getHotel().getCity(), dateFromValue, dateToValue)
                )

                .collect(Collectors.toList());

        return rooms.stream()
                .map(room -> modelMapper.map(room, RoomDto.class))
                .map(room -> setPriceInUserCurrency(room, userLocale))
                .collect(Collectors.toList());
    }

    private RoomDto setPriceInUserCurrency(RoomDto room, String userCurrency) {
        room.setPricePerNightInUserCurrency(
                currencyService.getValueInOtherCurrency(
                        room.getHotel().getCurrency(),
                        userCurrency,
                        room.getPricePerNight()
                )
        );
        return room;
    }

    @Transactional(readOnly = true)
    public List<RoomDto> getAllRoomsInHotelWithCriteria(Long hotelId, String name, Double guestsNumberValue, Double pricePerNightValue, Double tempMinValue) {
        return roomRepository.getAllByHotel_Id(hotelId)
                .stream()
                .map(hotel -> modelMapper.map(hotel, RoomDto.class))
                .collect(Collectors.toList());
    }


    private boolean tempMatch(Double tempMinValue, String country, String city, LocalDate dateFromValue, LocalDate dateToValue) {
        if(tempMinValue == null) return true;
        else return tempMinValue <= weatherService.getAverageTemperatureForRoomInGivenDates(country, city, dateFromValue, dateToValue);

    }


    private boolean guestsNumberMatch(Double roomCapacity, Double guestsNumber){
        if(guestsNumber == null) return true;
        else return roomCapacity >= guestsNumber;
    }

    private boolean priceMatch(Double roomPrice, Double maximumPrice){
        if(maximumPrice == null) return true;
        else return roomPrice >= maximumPrice;
    }

    private boolean reservationsMatch(RoomDto roomDto, LocalDate dateFrom, LocalDate dateTo) {
        return roomDto.getReservations().stream()
                .noneMatch(reservation -> datesOverlaps(reservation.getDateFrom(), reservation.getDateTo(), dateFrom, dateTo));
    }

    private boolean datesOverlaps(LocalDate dateFrom1, LocalDate dateTo1, LocalDate dateFrom2, LocalDate dateTo2) {
        return (dateFrom2.isAfter(dateFrom1) || dateFrom2.equals(dateFrom1)) &&
                (dateFrom2.isBefore(dateTo1) || dateFrom2.equals(dateTo1))  ||
                (dateTo2.isAfter(dateFrom1) || dateTo2.equals(dateFrom1)) &&
                (dateTo2.isBefore(dateTo1) || dateTo2.equals(dateTo1));
    }





}
