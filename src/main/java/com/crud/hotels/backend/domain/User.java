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

    @OneToMany(mappedBy = "user",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Reservation> reservations;
}
