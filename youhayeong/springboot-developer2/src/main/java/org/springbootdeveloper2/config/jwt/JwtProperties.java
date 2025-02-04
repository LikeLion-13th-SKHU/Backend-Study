package org.springbootdeveloper2.config.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component // 해당 클래스를 빈으로 등록하여 의존성 주입이 가능하도록 함
@ConfigurationProperties("jwt") // yml에서 'jwt'로 시작하는 설정 값을 자동으로 매핑
public class JwtProperties {
    // yml 파일에서 설정한 값들이 매핑됨
    private String issuer;
    private String secretKey;
}
