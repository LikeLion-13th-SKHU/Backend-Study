package org.springbootdeveloper2.repository;

import org.springbootdeveloper2.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // email로 사용자 정보 가져옴 - jpa 메소드 사용
    Optional<User> findByEmail(String email);
}
