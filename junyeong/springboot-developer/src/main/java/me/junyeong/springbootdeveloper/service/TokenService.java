package me.junyeong.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.junyeong.springbootdeveloper.config.jwt.TokenProvider;
import me.junyeong.springbootdeveloper.domain.User;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    public String createNewAccessToken(String refreshToken){
        if(!tokenProvider.validToken(refreshToken)){
            throw new IllegalArgumentException("Refresh Token이 유효하지 않습니다.");
        }
        Long uesrId = refreshTokenService.findByRefreshToken(refreshToken).getUserId();
        User user = userService.findById(uesrId);

        return tokenProvider.generateToken(user, Duration.ofHours(2));
    }
}
