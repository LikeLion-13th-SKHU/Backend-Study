package org.springbootdeveloper2.service;

import lombok.RequiredArgsConstructor;
import org.springbootdeveloper2.config.jwt.TokenProvider;
import org.springbootdeveloper2.domain.User;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    // 전달 받은 리프레시 토큰으로 토큰 유효성 검사 -> 유효한 토큰일 때 사용자 id 찾기 -> generateNewToken
    public String createNewAccessToken(String refreshToken) {
        // 토큰 유효성 검사
        if(!tokenProvider.validToken(refreshToken)) {
            throw new IllegalArgumentException("Unexpected token");
        }

        // 리프레시 토큰으로 사용자 id 찾기
        Long userId = refreshTokenService.findByRefreshToken(refreshToken).getUserId();

        // userId로 사용자 찾아서 user 객체 생성
        User user = userService.findById(userId);

        // 토큰 제공자의 generateToken으로 새로운 액세스 토큰 생성
        return tokenProvider.generateToken(user, Duration.ofHours(2));
    }
}
