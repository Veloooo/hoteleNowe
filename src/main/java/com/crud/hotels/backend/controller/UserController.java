package com.crud.hotels.backend.controller;

import com.crud.hotels.backend.domain.UserReport;
import com.crud.hotels.backend.dto.UserDto;
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
    public UserDto getUser() {
        return null;
    }

    @GetMapping(path = "/report/{username}")
    public UserReport getAllReportsForUser(@PathVariable String username) {
        //return userService.method()
        return null;
    }

    @GetMapping(path = "/report/{username}")
    public List<UserReport> getAllReportsForDate(@PathVariable String username, @RequestParam LocalDate reportDate) {
        //return userService.method()
        return null;
    }

    @GetMapping(path = "/report/{username}")
    public List<UserReport> getAllReportsForDate(@PathVariable String username, @RequestParam LocalDate reportDateFrom,
                                                 @RequestParam LocalDate reportDateTo) {
        //return userService.method()
        return null;
    }
}
