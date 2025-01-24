package org.springbootdeveloper2.service;

import lombok.RequiredArgsConstructor;
import org.springbootdeveloper2.domain.User;
import org.springbootdeveloper2.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {
    // 스프링 시큐리티에서 사용자 정보를 가져오는 인터페이스

    private final UserRepository userRepository;

    // 사용자 이름(email)로 사용자 정보 가져오는 메소드
    // UserDetailsService를 상속받음으로써 필수로 구현해야 하는 loadUser~ 메소드 구현
    @Override
    public User loadUserByUsername(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow( () -> new IllegalArgumentException((email)));
    }

    /*
    public interface UserDetailsService {
        UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    }*/
}
