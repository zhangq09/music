package com.study.music.repository;

import com.study.music.entity.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User,String> {

    User findByUsername(String username);

    Optional<User> findUserByUsername(String username);
}
