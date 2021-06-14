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
}
