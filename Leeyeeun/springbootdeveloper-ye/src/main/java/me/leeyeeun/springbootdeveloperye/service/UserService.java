package me.leeyeeun.springbootdeveloperye.service;

import lombok.RequiredArgsConstructor;
import me.leeyeeun.springbootdeveloperye.domain.User;
import me.leeyeeun.springbootdeveloperye.dto.request.AddUserRequest;
import me.leeyeeun.springbootdeveloperye.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Long save(AddUserRequest addUserRequest) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return userRepository.save(User.builder()
                .email(addUserRequest.getEmail())
                // 패스워드 암호화
                .password(encoder.encode(addUserRequest.getPassword()))
                .build()).getId();
    }

    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("Unexpected user"));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow( () -> new IllegalArgumentException("Unexpected user"));
    }
}