package org.project.Service;

import org.project.Dao.UserDao;
import org.project.Dto.AuthDto.LoginDto;
import org.project.Dto.AuthDto.RegisterDto;
import org.project.Dto.Mapper.UserMapper;
import org.project.Entity.User;
import org.project.Enum.Role;
import org.project.Util.HashKeyword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


// COMPLETE LATER

@Service
public class AuthService {
    UserDao userDao;

    @Autowired
    public AuthService(UserDao userDao) {
        this.userDao = userDao;
    }

    public LoginDto getUserToLoginDto(User user) {
        return UserMapper.INSTANCE.userToLoginDto(user);
    }

    public User getLoginDtoToUser(LoginDto loginDto) {
        return UserMapper.INSTANCE.loginDtoToUser(loginDto);
    }

    public RegisterDto getUserToRegisterDto(User user) {
        return UserMapper.INSTANCE.userToRegisterDto(user);
    }

    public User getRegisterDtoToUser(RegisterDto registerDto) {
        return UserMapper.INSTANCE.registerDtoToUser(registerDto);
    }

    public String register(User user) {
        Optional<User> existingUser = userDao.findUserByEmail(user.getEmail());
        if (existingUser.isEmpty()) {
            String hashedPass = HashKeyword.hash(user.getPassword(), 10);
            user.setPassword(hashedPass);
            user.setRole(Role.USER);
            User newUser = userDao.save(user);
            return "User Created successfully! \nUser: id - " + newUser.getId();
        }
        return "Email already exists!";
    }

    public String login(User user) {
        Optional<User> existingUser = userDao.findUserByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            String hashedPassword = existingUser.get().getPassword();
            if (HashKeyword.check(user.getPassword(), hashedPassword)) {
                return "User Logged in successfully!";
            }
        }
        return "Wrong Credentials!";
    }
}
