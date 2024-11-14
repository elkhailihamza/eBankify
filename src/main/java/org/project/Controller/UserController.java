package org.project.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.project.Dto.request.UserReqDto;
import org.project.Entity.Transaction;
import org.project.Entity.User;
import org.project.Service.TransactionService;
import org.project.Service.UserService;
import org.project.Util.HashKeyword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final TransactionService transactionService;
    private final UserService userService;

    @Autowired
    public UserController(TransactionService transactionService, UserService userService) {
        this.transactionService = transactionService;
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
        return ResponseEntity.status(HttpStatus.CONFLICT).body("User with same email already exists!");
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
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("An error has occurred!");
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteUser(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        Optional<User> userOpt = userService.findUserById(userId);
        if (userOpt.isPresent()) {
            userService.deleteUser(userOpt.get());
            return ResponseEntity.ok("Deleted User!");
        }
        return ResponseEntity.ok("Error has occured");
    }

}