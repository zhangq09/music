package com.study.music.controller;

import com.study.music.dto.UserCreateDto;
import com.study.music.dto.UserDto;
import com.study.music.mapper.UserMapper;
import com.study.music.service.UserService;
import com.study.music.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    UserService userService;

    UserMapper userMapper;

    @GetMapping("/")
    List<UserVo> list(){
        return userService.list()
                .stream().map(userMapper::toVo).collect(Collectors.toList());
    }

    @PostMapping("/")
    UserVo create(@RequestBody UserCreateDto userCreateDto){
        return userMapper.toVo(userService.create(userCreateDto));
    }

    @Autowired
    public void setUserService(UserService userService){
        this.userService=userService;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper){
        this.userMapper=userMapper;
    }
}
