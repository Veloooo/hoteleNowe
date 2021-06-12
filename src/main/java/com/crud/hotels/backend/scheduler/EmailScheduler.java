package com.crud.hotels.backend.scheduler;

import com.crud.hotels.backend.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailScheduler {

    private EmailService emailService;

    @Autowired
    public EmailScheduler(EmailService emailService) {
        this.emailService = emailService;
    }

    //Scheduled wysylanie maili do wszystkich uzytkownikow co x czasu
//    @Scheduled(cron = "0 23 16 * * *")
//    public void sendInformationEmail() {
//        long size = hotelRepository.count();
//        if (size==1){
//            this.TASK="task";
//        }
//        emailService.send(
//                new Mail(
//                        adminConfig.getAdminMail(),
//                        SUBJECT,
//                        "Currently in database you got: " + size + TASK,
//                        null
//                )
//        );
//    }
}
