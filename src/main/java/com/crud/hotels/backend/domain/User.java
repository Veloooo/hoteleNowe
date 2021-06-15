package com.crud.hotels.backend.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "users")
@RequiredArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NonNull
    private String login;

    @Column
    @NonNull
    private String password;

    @Column
    @NonNull
    private String role;

    @OneToMany(mappedBy = "user",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            orphanRemoval = true)
    private List<Reservation> reservations;

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
        reservation.setUser(this);
    }

    public void removeReservation(Reservation reservation) {
        reservations.remove(reservation);
        reservation.setUser(null);
    }

    @OneToMany(mappedBy = "owner",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            orphanRemoval = true)
    private List<Hotel> hotels;

    public void addHotel(Hotel hotel) {
        hotels.add(hotel);
        hotel.setOwner(this);
    }

    public void removeHotel(Hotel hotel) {
        hotels.remove(hotel);
        hotel.setOwner(null);
    }


    @OneToMany(mappedBy = "owner",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            orphanRemoval = true)
    private List<UserReport> reports;


    public void addReport(UserReport report) {
        reports.add(report);
        report.setOwner(this);
    }

    public void removeReport(UserReport report) {
        reports.remove(report);
        report.setOwner(null);
    }


    public static class Builder {
        private String login;
        private String password;
        private String role;
        private List<Reservation> reservations;
        private List<Hotel> hotels;

        public User.Builder login(String login) {
            this.login = login;
            return this;
        }

        public User.Builder password(String password) {
            this.password = password;
            return this;
        }

        public User.Builder role(String role) {
            this.role = role;
            return this;
        }

        public User.Builder reservations(List<Reservation> reservations) {
            this.reservations = reservations;
            return this;
        }

        public User.Builder hotels(List<Hotel> hotels) {
            this.hotels = hotels;
            return this;
        }

        public User build() {
            User user = new User();
            user.setPassword(this.password);
            user.setLogin(this.login);
            user.setRole(this.role);
            user.setHotels(this.hotels);
            user.setReservations(this.reservations);
            return user;
        }
    }
}
