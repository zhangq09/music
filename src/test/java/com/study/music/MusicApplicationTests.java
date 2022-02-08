package com.study.music;

import com.study.music.entity.User;
import com.study.music.enums.Gender;
import com.study.music.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class MusicApplicationTests {

    @Autowired
    UserRepository userRepository;

    @Test
    void findByUsername(){
        User user =new User();
        user.setUsername("zq");
        user.setNickname("zq09");
        user.setEnabled(true);
        user.setLocked(false);
        user.setGender(Gender.MALE);
        user.setPassword("zq969811.");
        user.setLastLoginIp("0.0.0.1");
        user.setLastLoginTime(new Date());

        User saveUser = userRepository.save(user);
        User result = userRepository.findByUsername("zq");
        System.out.println(result.toString());
        System.out.println(saveUser.toString());

    }

}
