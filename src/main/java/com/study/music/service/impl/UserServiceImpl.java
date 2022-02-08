package com.study.music.service.impl;

import com.study.music.dto.UserDto;
import com.study.music.mapper.UserMapper;
import com.study.music.repository.UserRepository;
import com.study.music.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    UserMapper userMapper;

    @Override
    public List<UserDto> list() {
        return userRepository.findAll()
                .stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
         this.userMapper=userMapper;
    }
}
