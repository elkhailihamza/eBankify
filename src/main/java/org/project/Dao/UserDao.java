package org.project.Dao;

import org.project.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);
    boolean existsUserByEmail(String email);
}
