package com.example.springbootdeveloper.config.oauth;

import com.example.springbootdeveloper.domain.User;
import com.example.springbootdeveloper.repository.UserRepository;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OAuth2UserCustomService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;

    // 리소스 서버에서 보내주는 사용자 정보 불러오는 메서드 -> 사용자 조회
    // users 테이블에 사용자 정보 있다면 이름 업데이트
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // 요청을 바탕으로 유저 정보를 담은 객체 반환
        OAuth2User user = super.loadUser(userRequest);
        saveOrUpdate(user);
        return user;
    }

    // users 테이블에 사용자 정보 있다면 이름 업데이트
    // users 테이블에 사용자 정보 없다면 users 테이블에 회원 데이터 추가
    private User saveOrUpdate(OAuth2User oAuth2User) {
        Map<String, Object> attributes = oAuth2User.getAttributes();
        String email = (String) attributes.get("email");
        String name = (String) attributes.get("name");
        User user = userRepository.findByEmail(email)
            .map(entity -> entity.update(name))
            .orElse(User.builder()
                .email(email)
                .nickname(name)
                .build());
        return userRepository.save(user);
    }
}
