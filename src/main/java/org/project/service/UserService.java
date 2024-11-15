package org.project.service;

import org.project.dao.UserDao;
import org.project.dto.mapper.UserMapper;
import org.project.dto.request.UserReqDto;
import org.project.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User toUser(UserReqDto userReqDto) {
        return UserMapper.INSTANCE.toUser(userReqDto);
    }

    public Optional<User> findUserById(long id) {
        return userDao.findById(id);
    }

    public List<User> fetchAllUsers() {
        return userDao.findAll();
    }

    public User saveUser(User user) {
        return userDao.save(user);
    }

    public void deleteUser(User user) {
        userDao.delete(user);
    }

    public boolean userExistsById(long id) {
        return userDao.existsById(id);
    }

    public boolean userExistsByEmail(String email) {
        return userDao.existsUserByEmail(email);
    }
}
