package com.crud.hotels.backend.domain;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "hotels")
@RequiredArgsConstructor
@NoArgsConstructor
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NonNull
    private String name;

    @Column
    @NonNull
    private String country;

    @Column
    @NonNull
    private String city;

    @Column
    @NonNull
    private Integer totalRooms;

    @Column
    @NonNull
    private Integer freeRooms;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(mappedBy = "hotel",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    List<Room> rooms;

    public static class Builder {
        private String name;

        private String country;

        private String city;

        private Integer totalRooms;

        private Integer freeRooms;

        public Builder hotelName(String name) {
            this.name = name;
            return this;
        }

        public Builder hotelCountry(String country) {
            this.country = country;
            return this;
        }

        public Builder hotelCity(String city) {
            this.city = city;
            return this;
        }

        public Builder hotelTotalRooms(Integer totalRooms) {
            this.totalRooms = totalRooms;
            return this;
        }

        public Builder hotelFreeRooms(Integer freeRooms) {
            this.freeRooms = freeRooms;
            return this;
        }

        public Hotel build() {
            Hotel hotel = new Hotel();
            hotel.setName(this.name);
            hotel.setCountry(this.country);
            hotel.setCity(this.city);
            hotel.setFreeRooms(this.freeRooms);
            hotel.setTotalRooms(this.totalRooms);
            return hotel;
        }
    }
}
