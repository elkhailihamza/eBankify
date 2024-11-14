package org.project.Controller;

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
        if (userService.userExistsByEmail(userReqDto.getEmail())) {
            String hashedPassword = HashKeyword.hash(userReqDto.getPassword(), 10);
            User user = User.builder()
                    .name(userReqDto.getName())
                    .surname(userReqDto.getSurname())
                    .email(userReqDto.getEmail())
                    .password(hashedPassword)
                    .age(userReqDto.getAge())
                    .role(userReqDto.getRole())
                    .build();
            user = userService.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User created: id - "+user.getId());
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("User with same email already exists!");
    }

}
