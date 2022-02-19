package com.study.music.controller;

import com.study.music.dto.TokenCreateRequest;
import com.study.music.mapper.FileUploadMapper;
import com.study.music.service.FileService;
import com.study.music.service.UserService;
import com.study.music.vo.FileUploadVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/tokens")
public class TokenController {

    UserService userService;

    FileService fileService;

    FileUploadMapper fileUploadMapper;

    @PostMapping()
    public String create(@Validated @RequestBody TokenCreateRequest tokenCreateRequest) {
        return userService.createToken(tokenCreateRequest);
    }

    @GetMapping("/sts_token")
    public FileUploadVo getStsToken() throws IOException {
        return fileUploadMapper.toVo((fileService.getToken()));
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private void setFileUploadMapper(FileUploadMapper fileUploadMapper) {
        this.fileUploadMapper = fileUploadMapper;
    }

    @Autowired
    private void setFileService(FileService fileService) {
        this.fileService = fileService;
    }
}
