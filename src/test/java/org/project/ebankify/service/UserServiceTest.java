package org.project.ebankify.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.project.ebankify.dao.UserDao;
import org.project.ebankify.dto.request.UserReqDto;
import org.project.ebankify.entity.User;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserService userService;

    private UserReqDto userReqDto;

    @BeforeEach
    public void setup() {
        userReqDto = new UserReqDto("John", "Doe", "john.doe@example.com", "password123", 25, null);
    }

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setId(1L);

        when(userDao.save(any(User.class))).thenReturn(user);

        User savedUser = userService.saveUser(user);
        assertNotNull(savedUser);
        assertEquals(1L, savedUser.getId());
    }

    @Test
    public void testFindUserById() {
        User user = new User();
        user.setId(1L);

        when(userDao.findById(anyLong())).thenReturn(Optional.of(user));

        Optional<User> userOpt = userService.findUserById(1L);
        assertTrue(userOpt.isPresent());
        assertEquals(1L, userOpt.get().getId());
    }

    @Test
    public void testUserExistsByEmail() {
        when(userDao.existsUserByEmail("john.doe@example.com")).thenReturn(true);

        boolean exists = userService.userExistsByEmail("john.doe@example.com");
        assertTrue(exists);
    }

    @Test
    public void testToUser() {
        User user = userService.toUser(userReqDto);
        assertNotNull(user);
        assertEquals(userReqDto.getEmail(), user.getEmail());
    }
}
