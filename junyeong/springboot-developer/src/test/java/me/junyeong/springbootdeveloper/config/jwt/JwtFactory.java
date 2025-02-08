package me.junyeong.springbootdeveloper.config.jwt;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import me.junyeong.springbootdeveloper.config.jwt.JwtProperties;

import java.time.Duration;
import java.util.Date;
import java.util.Map;

import static java.util.Collections.emptyMap;

@Getter
public class JwtFactory {
    private String subject;
    private Date issuedAt;
    private Date expiration;
    private Map<String, Object> claims;

    public JwtFactory(String subject, Date issuedAt, Date expiration, Map<String, Object> claims) {
        this.subject = subject != null ? subject : "test@email.com";
        this.issuedAt = issuedAt != null ? issuedAt : new Date();
        this.expiration = expiration != null ? expiration : new Date(new Date().getTime() + Duration.ofDays(14).toMillis());
        this.claims = claims != null ? claims : emptyMap();
    }

    public static JwtFactoryBuilder builder() {
        return new JwtFactoryBuilder();
    }

    public static class JwtFactoryBuilder {
        private String subject;
        private Date issuedAt;
        private Date expiration;
        private Map<String, Object> claims;

        public JwtFactoryBuilder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public JwtFactoryBuilder issuedAt(Date issuedAt) {
            this.issuedAt = issuedAt;
            return this;
        }

        public JwtFactoryBuilder expiration(Date expiration) {
            this.expiration = expiration;
            return this;
        }

        public JwtFactoryBuilder claims(Map<String, Object> claims) {
            this.claims = claims;
            return this;
        }

        public JwtFactory build() {
            return new JwtFactory(subject, issuedAt, expiration, claims);
        }
    }

    public static JwtFactory withDefaultValues() {
        return JwtFactory.builder().build();
    }

    public String createToken(JwtProperties jwtProperties) {
        return Jwts.builder()
                .setSubject(subject)
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(jwtProperties.getIssuer())
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .compact();
    }
}
