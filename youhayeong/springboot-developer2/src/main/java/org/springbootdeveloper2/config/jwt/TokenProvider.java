package org.springbootdeveloper2.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springbootdeveloper2.domain.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class TokenProvider {
    // 토큰 생성, 유효성 검사, 토큰에서 필요한 정보를 가져오는 클래스
    // 사용자 인증을 처리하는 비즈니스 로직 포함 o = @Service 어노테이션

    private final JwtProperties jwtProperties;

    public String generateToken(User user, Duration expiredAt) {
        Date now = new Date();
        return makeToken(new Date(now.getTime() + expiredAt.toMillis()), user);
    }

    // 1. JWT 토큰 생성 메서드
    private String makeToken(Date expiry, User user) {
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // 헤더 typ: "JWT"
                .setIssuer(jwtProperties.getIssuer()) // 내용 iss: yml에서 매핑
                .setIssuedAt(now)   // 내용 iat: 현재 시간
                .setExpiration(expiry)  // 내용 exp: expiry 멤버 변수값
                .setSubject(user.getEmail())    // 내용 sub: 유저 이메일
                .claim("id", user.getId())  // 클레임 id: 유저 id
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                // 서명: 비밀값과 함께 해시값을 HS256 방식으로 암호화
                .compact(); // 압축해서 토큰 생성
    }

    // 2. JWT 토큰 유효성 검증 메서드
    public boolean validToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(jwtProperties.getSecretKey())    // 비밀값으로 복호화
                    .parseClaimsJws(token);

            return true;
        } catch (Exception e) {
            return false; // 유효하지 않은 토큰
        }
    }

    // 3. 토큰 기반으로 인증 정보 가져오는 메서드
    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token); // 토큰에서 클레임 정보 추출
        Set<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")); // 권한 설정
        return new UsernamePasswordAuthenticationToken(new org.springframework.security.core.userdetails.User(claims.getSubject
                (), "", authorities), token, authorities);
    }

    // 4. 토큰 기반으로 유저 id 가져오는 메서드
    public Long getUserId(String token) {
        Claims claims = getClaims(token);
        return claims.get("id", Long.class);
    }

    // 3 - 토큰에서 클레임 정보 추출 메서드
    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token)
                .getBody();
    }
}