package com.crud.hotels.backend.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity(name = "user_reports")
@RequiredArgsConstructor
@NoArgsConstructor
public class UserReport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NonNull
    private LocalDate reportDate;

    @Column
    @NonNull
    private Integer roomsRented;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    public static class Builder {
        private LocalDate reportDate;
        private Integer roomsRented;
        private User owner;


        public UserReport.Builder reportDate(LocalDate reportDate) {
            this.reportDate = reportDate;
            return this;
        }

        public UserReport.Builder roomsRented(Integer roomsRented) {
            this.roomsRented = roomsRented;
            return this;
        }

        public UserReport.Builder owner(User owner) {
            this.owner = owner;
            return this;
        }


        public UserReport build() {
            UserReport userReport = new UserReport();
            userReport.setReportDate(this.reportDate);
            userReport.setOwner(this.owner);
            userReport.setRoomsRented(this.roomsRented);
            return userReport;
        }
    }

}
