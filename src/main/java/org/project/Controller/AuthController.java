package org.project.Controller;

import org.project.Dto.AuthDto.LoginDto;
import org.project.Dto.AuthDto.RegisterDto;
import org.project.Entity.User;
import org.project.Enum.Role;
import org.project.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @ResponseBody
    public String login(@RequestBody LoginDto loginDto) {
        User user = authService.getLoginDtoToUser(loginDto);
        return authService.login(user);
    }

    @PostMapping("/register")
    @ResponseBody
    public String register(@RequestBody RegisterDto registerDto) {
        User user = authService.getRegisterDtoToUser(registerDto);
        user.setRole(Role.USER);
        return authService.register(user);
    }
}
