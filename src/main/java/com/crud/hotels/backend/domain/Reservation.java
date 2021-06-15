package com.crud.hotels.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private LocalDate createDate;

    @Column
    private LocalDate dateFrom;

    @Column
    private LocalDate dateTo;

    @Column
    private Integer daysTotal;

    @Column
    private Integer priceTotal;

    @Column
    private Boolean paid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;


    public static class Builder {
        private LocalDate createDate;
        private LocalDate dateFrom;
        private LocalDate dateTo;
        private User user;
        private Room room;
        private Integer daysTotal;
        private Integer priceTotal;
        private Boolean paid;


        public Reservation.Builder daysTotal(Integer daysTotal) {
            this.daysTotal = daysTotal;
            return this;
        }

        public Reservation.Builder priceTotal(Integer priceTotal) {
            this.priceTotal = priceTotal;
            return this;
        }

        public Reservation.Builder paid(Boolean paid) {
            this.paid = paid;
            return this;
        }

        public Reservation.Builder dateFrom(LocalDate dateFrom) {
            this.dateFrom = dateFrom;
            return this;
        }

        public Reservation.Builder dateTo(LocalDate dateTo) {
            this.dateTo = dateTo;
            return this;
        }

        public Reservation.Builder createDate(LocalDate createDate) {
            this.createDate = createDate;
            return this;
        }

        public Reservation.Builder user(User user) {
            this.user = user;
            return this;
        }

        public Reservation.Builder room(Room room) {
            this.room = room;
            return this;
        }

        public Reservation build() {
            Reservation reservation = new Reservation();
            reservation.setCreateDate(this.createDate);
            reservation.setDateFrom(this.dateFrom);
            reservation.setDateTo(this.dateTo);
            reservation.setUser(this.user);
            reservation.setRoom(this.room);
            reservation.setDaysTotal(this.daysTotal);
            reservation.setPriceTotal(this.priceTotal);
            reservation.setPaid(this.paid);
            return reservation;
        }
    }
}
