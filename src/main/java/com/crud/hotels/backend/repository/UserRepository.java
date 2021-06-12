package com.crud.hotels.backend.repository;

import com.crud.hotels.backend.domain.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Hotel, Long> {

}
