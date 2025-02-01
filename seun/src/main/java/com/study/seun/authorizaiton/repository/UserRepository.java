package com.study.seun.authorizaiton.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.study.seun.authorizaiton.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
