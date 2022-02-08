package com.study.music.repository;

import com.study.music.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,String> {

    User findByUsername(String username);
}
