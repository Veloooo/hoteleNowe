package com.crud.hotels.backend.scheduler;


import com.crud.hotels.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReportScheduler {

    private UserService userService;

    @Autowired
    public ReportScheduler(UserService userService) {
        this.userService = userService;
    }

    @Scheduled(cron = "0 0 * * *")
    public void generateReports() {
        userService.generateReportsForOwner();
    }

}
