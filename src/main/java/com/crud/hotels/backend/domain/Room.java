package com.crud.hotels.backend.domain;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "rooms")
@RequiredArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NonNull
    private String name;

    @Column
    @NonNull
    private Double guestsNumber;

    @Column
    @NonNull
    private Double pricePerNight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @OneToMany(mappedBy = "room",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            orphanRemoval = true)
    private List<Reservation> reservations;

    public void removeReservation(Reservation reservation){
        reservation.setRoom(null);
        reservations.remove(reservation);
    }

    public static class Builder {
        private String name;

        private Double guestsNumber;

        private Double pricePerNight;

        private Hotel hotel;

        private List<Reservation> reservations;

        public Room.Builder name(String name) {
            this.name = name;
            return this;
        }

        public Room.Builder guestsNumber(Double guestsNumber) {
            this.guestsNumber = guestsNumber;
            return this;
        }

        public Room.Builder pricePerNight(Double pricePerNight) {
            this.pricePerNight = pricePerNight;
            return this;
        }

        public Room.Builder reservations(List<Reservation> reservations) {
            this.reservations = reservations;
            return this;
        }

        public Room.Builder hotel(Hotel hotel) {
            this.hotel = hotel;
            return this;
        }

        public Room build() {
            Room room = new Room();
            room.setGuestsNumber(this.guestsNumber);
            room.setName(this.name);
            room.setHotel(this.hotel);
            room.setPricePerNight(this.pricePerNight);
            room.setReservations(this.reservations);
            return room;
        }
    }

}
