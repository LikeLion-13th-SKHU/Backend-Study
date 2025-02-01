package com.example.springbootdeveloper.repository;

import com.example.springbootdeveloper.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); // email로 사용자 정보 가져옴
    // FROM users WHERE email = #{email}

}
