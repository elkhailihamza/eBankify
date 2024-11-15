package org.project.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.project.dto.request.authdto.LoginDto;
import org.project.dto.request.authdto.RegisterDto;
import org.project.entity.User;
import org.project.type.Role;
import org.project.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto, HttpServletRequest request) {
        User user = authService.getLoginDtoToUser(loginDto);
        return authService.login(user, request.getSession(true));
    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        User user = authService.getRegisterDtoToUser(registerDto);
        user.setRole(Role.USER);
        return authService.register(user);
    }
}
