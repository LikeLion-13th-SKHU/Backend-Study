package me.leeyeeun.springbootdeveloperye.repository;

import me.leeyeeun.springbootdeveloperye.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
