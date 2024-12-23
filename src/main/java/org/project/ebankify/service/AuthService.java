package org.project.ebankify.service;

import jakarta.servlet.http.HttpSession;
import org.project.ebankify.dao.UserDao;
import org.project.ebankify.dto.request.authdto.LoginDto;
import org.project.ebankify.dto.request.authdto.RegisterDto;
import org.project.ebankify.dto.mapper.UserMapper;
import org.project.ebankify.entity.User;
import org.project.ebankify.exception.EntityDataConflictException;
import org.project.ebankify.exception.UnexpectedErrorException;
import org.project.ebankify.type.Role;
import org.project.ebankify.util.HashKeyword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserDao userDao;

    public AuthService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getLoginDtoToUser(LoginDto loginDto) {
        return UserMapper.INSTANCE.toUser(loginDto);
    }

    public User getRegisterDtoToUser(RegisterDto registerDto) {
        return UserMapper.INSTANCE.toUser(registerDto);
    }

    public ResponseEntity<String> register(User user) {
        String email = user.getEmail();
        boolean userExists = userDao.existsUserByEmail(email);
        if (!userExists) {
            String hashedPass = HashKeyword.hash(user.getPassword(), 10);
            user.setPassword(hashedPass);
            user.setRole(Role.USER);
            userDao.save(user);

            return ResponseEntity.status(HttpStatus.CREATED).body("User Created successfully!");
        }
        throw new EntityDataConflictException("Email already exists!");
    }

    public ResponseEntity<String> login(User user, HttpSession session) {
        Optional<User> existingUser = userDao.findUserByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            String hashedPassword = existingUser.get().getPassword();
            if (HashKeyword.check(user.getPassword(), hashedPassword)) {
                session.setAttribute("AUTH.id", existingUser.get().getId());
                return ResponseEntity.ok("User Logged in successfully!");
            }
        }
        throw new UnexpectedErrorException("Wrong credentials!");
    }
}
