package org.project.Service;

import org.project.Dao.UserDao;
import org.project.Entity.User;
import org.project.Util.HashKeyword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


// COMPLETE LATER

@Service
public class AuthService {
    UserDao userDao;

    @Autowired
    public AuthService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void register(String name, String surname, String email, String password, int age) {
        User user = userDao.findUserByEmail(email);
        if (user == null) {
            String hashedPass = HashKeyword.hash(password, 13);
            user = User.builder()
                    .name(name)
                    .surname(surname)
                    .email(email)
                    .password(hashedPass)
                    .age(age)
                    .build();
            userDao.save(user);
        }
    }

    public void login(String email, String password) {
        User user = userDao.findUserByEmail(email);
        if (user != null) {
            String hashedPassword = user.getPassword();
            if (HashKeyword.check(hashedPassword, password)) {
                // success
            }
        }
    }
}
