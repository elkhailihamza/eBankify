package org.project.Service;

import jakarta.servlet.http.HttpSession;
import org.project.Dao.UserDao;
import org.project.Dto.request.AuthDto.LoginDto;
import org.project.Dto.request.AuthDto.RegisterDto;
import org.project.Dto.Mapper.UserMapper;
import org.project.Entity.User;
import org.project.Enum.Role;
import org.project.Util.HashKeyword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserDao userDao;

    @Autowired
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
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists!");
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
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong credentials!");
    }
}
