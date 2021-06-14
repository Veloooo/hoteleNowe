package com.crud.hotels.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "room_id")
    private Room room;


    public static class Builder {
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
