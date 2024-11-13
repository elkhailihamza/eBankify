package org.project.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.project.Dto.request.AuthDto.LoginDto;
import org.project.Dto.request.AuthDto.RegisterDto;
import org.project.Entity.User;
import org.project.Enum.Role;
import org.project.Service.AuthService;
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
