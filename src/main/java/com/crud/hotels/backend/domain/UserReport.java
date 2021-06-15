package com.crud.hotels.backend.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

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

}
