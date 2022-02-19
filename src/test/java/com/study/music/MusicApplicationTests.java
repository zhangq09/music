package com.study.music;

import com.study.music.service.StorageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Date;

@SpringBootTest
class MusicApplicationTests {

    @Autowired
    StorageService storageService;

    @Test
    void findByUsername() throws IOException {
        System.out.println(new Date(System.currentTimeMillis() + 1000));
    }


}
