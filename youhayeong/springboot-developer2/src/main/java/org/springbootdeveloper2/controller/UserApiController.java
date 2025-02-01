package org.springbootdeveloper2.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springbootdeveloper2.dto.request.AddUserRequest;
import org.springbootdeveloper2.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    // 사용자 저장한 뒤(회원가입) 로그인 페이지로 이동
    @PostMapping("/user")
    public String signup(AddUserRequest request) {
        userService.save(request); // 회원가입
        return "redirect:/login";
    }

    // 로그아웃
    @GetMapping("logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response
                , SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }
}
