package com.crud.hotels.backend.repository;

import com.crud.hotels.backend.domain.Reservation;
import com.crud.hotels.backend.domain.User;
import com.crud.hotels.backend.domain.UserReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserReportRepository extends JpaRepository<UserReport, Long> {
    List<UserReport> findAllByOwner(User user);
}
