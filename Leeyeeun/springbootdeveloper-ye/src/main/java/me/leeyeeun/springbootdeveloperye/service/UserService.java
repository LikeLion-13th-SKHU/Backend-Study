package me.leeyeeun.springbootdeveloperye.service;

import lombok.RequiredArgsConstructor;
import me.leeyeeun.springbootdeveloperye.domain.User;
import me.leeyeeun.springbootdeveloperye.dto.request.AddUserRequest;
import me.leeyeeun.springbootdeveloperye.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public Long save(AddUserRequest dto) {
        return userRepository.save(User.builder()
                .email(dto.getEmail())
                // 패스워드 암호화
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .build()).getId();
    }

    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("Unexpected user"));
    }
}