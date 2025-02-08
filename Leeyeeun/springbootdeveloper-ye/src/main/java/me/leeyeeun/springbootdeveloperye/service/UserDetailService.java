package me.leeyeeun.springbootdeveloperye.service;

import lombok.RequiredArgsConstructor;
import me.leeyeeun.springbootdeveloperye.domain.User;
import me.leeyeeun.springbootdeveloperye.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public User loadUserByUsername(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException(email));
    }
}
