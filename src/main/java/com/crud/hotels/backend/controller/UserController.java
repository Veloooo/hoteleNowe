package com.crud.hotels.backend.controller;

import com.crud.hotels.backend.domain.UserReport;
import com.crud.hotels.backend.dto.UserDto;
import com.crud.hotels.backend.dto.UserReportDto;
import com.crud.hotels.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/{username}")
    public UserDto getUser(@PathVariable String username) {
        return userService.getUserByLogin(username);
    }

    @GetMapping(path = "/report/{username}")
    public List<UserReportDto> getAllReportsForUser(@PathVariable String username) {
        return userService.getAllReportsForUser(username);
    }

}
