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
    private Integer floor;

    @Column
    @NonNull
    private Integer peopleSize;

    @Column
    @NonNull
    private Boolean available;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @OneToMany(mappedBy = "room",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Reservation> reservations;
}
