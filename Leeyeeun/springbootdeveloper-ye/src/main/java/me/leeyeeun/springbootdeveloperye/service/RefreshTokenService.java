package me.leeyeeun.springbootdeveloperye.service;

import lombok.RequiredArgsConstructor;

import me.leeyeeun.springbootdeveloperye.domain.RefreshToken;
import me.leeyeeun.springbootdeveloperye.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken findByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken).orElseThrow(
                () -> new IllegalArgumentException("Unexpected token"));
    }
}