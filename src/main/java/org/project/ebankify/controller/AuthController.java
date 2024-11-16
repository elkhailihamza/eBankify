package org.project.ebankify.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.project.ebankify.dto.request.authdto.LoginDto;
import org.project.ebankify.dto.request.authdto.RegisterDto;
import org.project.ebankify.entity.User;
import org.project.ebankify.type.Role;
import org.project.ebankify.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

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
