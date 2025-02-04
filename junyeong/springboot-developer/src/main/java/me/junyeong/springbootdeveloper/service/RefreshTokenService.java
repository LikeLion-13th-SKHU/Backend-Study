package me.junyeong.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.junyeong.springbootdeveloper.domain.RefreshToken;
import me.junyeong.springbootdeveloper.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken findByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("해당 Refresh Token을 찾을 수 없습니다."));
    }
}
