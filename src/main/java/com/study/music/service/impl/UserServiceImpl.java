package com.study.music.service.impl;

import com.study.music.dto.UserCreateDto;
import com.study.music.dto.UserDto;
import com.study.music.entity.User;
import com.study.music.enums.ExceptionType;
import com.study.music.exception.BizException;
import com.study.music.mapper.UserMapper;
import com.study.music.repository.UserRepository;
import com.study.music.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    UserMapper userMapper;

    PasswordEncoder passwordEncoder;

    @Override
    public List<UserDto> list() {
        return userRepository.findAll()
                .stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public UserDto create(UserCreateDto userCreateDto) {
        checkUsername(userCreateDto.getUsername());
        userCreateDto.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
        return userMapper.toDto(userRepository.save(userMapper.createEntity(userCreateDto)));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userByUsername = userRepository.findUserByUsername(username);
        if(!userByUsername.isPresent()){
            throw new BizException(ExceptionType.USER_NOT_FOUND);
        }
        return userByUsername.get();
    }

    private void checkUsername(String username){
        Optional<User> userByUsername = userRepository.findUserByUsername(username);
        if (userByUsername.isPresent()){
            throw new BizException(ExceptionType.USER_NAME_DUPLICATE);
        };
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
         this.userMapper=userMapper;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder){
        this.passwordEncoder=passwordEncoder;
    }
}
