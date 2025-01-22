package me.junyeong.springbootdeveloper.repository;

import java.util.Optional;
import me.junyeong.springbootdeveloper.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
