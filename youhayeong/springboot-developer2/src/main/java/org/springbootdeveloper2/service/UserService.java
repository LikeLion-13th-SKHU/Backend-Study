package org.springbootdeveloper2.service;

import lombok.RequiredArgsConstructor;
import org.springbootdeveloper2.domain.User;
import org.springbootdeveloper2.dto.request.AddUserRequest;
import org.springbootdeveloper2.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 회원 정보 추가
    public Long save(AddUserRequest addUserRequest) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return userRepository.save(User.builder()
                .email(addUserRequest.getEmail())
                // 패스워드 암호화
                .password(encoder.encode(addUserRequest.getPassword()))
                .build()).getId();
    }

    // 전달 받은 유저 id로 유저 검색해서 전달
    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("Unexpected user"));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow( () -> new IllegalArgumentException("Unexpected user"));
    }
}
