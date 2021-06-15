package com.crud.hotels.backend.service;

import com.crud.hotels.backend.domain.User;
import com.crud.hotels.backend.domain.UserReport;
import com.crud.hotels.backend.dto.HotelDto;
import com.crud.hotels.backend.dto.ReservationDto;
import com.crud.hotels.backend.dto.UserDto;
import com.crud.hotels.backend.dto.UserReportDto;
import com.crud.hotels.backend.repository.UserReportRepository;
import com.crud.hotels.backend.repository.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Getter
@Setter
public class UserService {

    private final UserRepository userRepository;
    private final UserReportRepository userReportRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper, UserReportRepository userReportRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.userReportRepository = userReportRepository;
    }


    @Transactional(readOnly = true)
    public UserDto getUserByLogin(String login) {
//        return modelMapper.map(userRepository.findUserByLogin(login), UserDto.class);
        User user = userRepository.findUserByLogin(login);
        return new UserDto(user.getId(),
                user.getLogin(),
                user.getPassword(),
                user.getRole(),
                user.getReservations().stream()
                        .map(reservation -> modelMapper.map(reservation, ReservationDto.class))
                        .collect(Collectors.toList()),
                user.getHotels().stream()
                        .map(hotel -> modelMapper.map(hotel, HotelDto.class))
                        .collect(Collectors.toList()));
    }

    @Transactional(readOnly = true)
    public void generateReportsForOwner() {
        List<User> owners = userRepository.findAllByHotelsNotEmpty();
        owners.stream()
                .map(owner ->
                {
                    owner.addReport(new UserReport.Builder()
                            .reportDate(LocalDate.now())
                            .roomsRented(
                                    (int) owner.getHotels().stream()
                                            .map(hotel -> hotel.getRooms().stream()
                                                    .map(room -> room.getReservations().stream()
                                                            .filter(reservation -> reservation.getCreateDate().equals(LocalDate.now()))
                                                            .count())).count())
                            .owner(owner)
                            .build());
                    return userRepository.save(owner);
                }).collect(Collectors.toList());
        ;
    }

    @Transactional(readOnly = true)
    public List<UserReportDto> getAllReportsForUser(String username) {
        return userReportRepository.findAllByOwner(userRepository.findUserByLogin(username)).stream().map(report -> modelMapper.map(report, UserReportDto.class)).collect(Collectors.toList());
    }


    @PostConstruct
    public void insertSampleData() {
        if (userRepository.count() == 0) {
            userRepository.saveAll(
                    Stream.of("Daniel,password,ROLE_USER",
                            "Krzysio,password,ROLE_USER",
                            "Bartek,password,ROLE_USER",
                            "Bogdan,password,ROLE_OWNER",
                            "Janusz,password,ROLE_OWNER",
                            "Zbycho,password,ROLE_ADMIN")
                            .map(name -> {
                                        String[] split = name.split(",");
                                        User user = new User();
                                        user.setLogin(split[0]);
                                        user.setPassword(split[1]);
                                        user.setRole(split[2]);
                                        return user;
                                    }
                            ).collect(Collectors.toList())
            );
        }
    }
}
