package com.crud.hotels.backend.repository;

import com.crud.hotels.backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByLogin(String login);

    List<User> findAllByHotelsNotEmpty();
}
