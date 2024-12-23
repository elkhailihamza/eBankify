package org.project.ebankify.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.project.ebankify.dto.request.UserReqDto;
import org.project.ebankify.entity.User;
import org.project.ebankify.exception.EntityDataConflictException;
import org.project.ebankify.exception.UnexpectedErrorException;
import org.project.ebankify.service.UserService;
import org.project.ebankify.util.HashKeyword;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createNewUser(@RequestBody UserReqDto userReqDto) {
        if (!userService.userExistsByEmail(userReqDto.getEmail())) {
            String hashedPassword = HashKeyword.hash(userReqDto.getPassword(), 10);
            User user = userService.toUser(userReqDto);
            user.setPassword(hashedPassword);
            user = userService.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User created: id - "+user.getId());
        }
        throw new EntityDataConflictException("User with same email already exists!");
    }

    @PostMapping("/update/{userId}")
    public ResponseEntity<String> modifyUser(@PathVariable long userId, @RequestBody UserReqDto userReqDto) {
        Optional<User> userOpt = userService.findUserById(userId);
        if (userOpt.isPresent() && !userService.userExistsByEmail(userReqDto.getEmail())) {
            User existingUser = userOpt.get();
            String hashedPassword = HashKeyword.hash(userReqDto.getPassword(), 10);
            User user = userService.toUser(userReqDto);
            user.setId(existingUser.getId());
            user.setPassword(hashedPassword);
            user = userService.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User modified: id - "+user.getId());
        }
        throw new UnexpectedErrorException("An error has occurred!");
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteUser(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        Optional<User> userOpt = userService.findUserById(userId);
        if (userOpt.isPresent()) {
            userService.deleteUser(userOpt.get());
            return ResponseEntity.ok("Deleted User!");
        }
        throw new EntityNotFoundException("User not found!");
    }

}