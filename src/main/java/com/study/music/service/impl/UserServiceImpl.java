package com.study.music.service.impl;

import com.study.music.dto.UserCreateDto;
import com.study.music.dto.UserDto;
import com.study.music.dto.UserUpdateRequest;
import com.study.music.entity.User;
import com.study.music.enums.ExceptionType;
import com.study.music.exception.BizException;
import com.study.music.mapper.UserMapper;
import com.study.music.repository.UserRepository;
import com.study.music.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
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
    public UserDetails loadUserByUsername(String username) {
        Optional<User> userByUsername = userRepository.findUserByUsername(username);
        if (!userByUsername.isPresent()) {
            throw new BizException(ExceptionType.USER_NOT_FOUND);
        }
        return userByUsername.get();
    }

    @Override
    public UserDto get(String id) {
        return userMapper.toDto(getUserById(id));
    }

    @Override
    public UserDto update(String id, UserUpdateRequest userUpdateRequest) {
        User user = getUserById(id);
        checkUsername(userUpdateRequest.getUsername());
        return userMapper.toDto(userRepository.save(userMapper.updateEntity(user, userUpdateRequest)));
    }

    @Override
    public Page<UserDto> search(Pageable pageable) {

        return userRepository.findAll(pageable).map(userMapper::toDto);
    }

    @Override
    public void delete(String id) {
        getUserById(id);
        userRepository.deleteById(id);
    }

    private void checkUsername(String username) {
        Optional<User> userByUsername = userRepository.findUserByUsername(username);
        if (userByUsername.isPresent()) {
            throw new BizException(ExceptionType.USER_NAME_DUPLICATE);
        }
        ;
    }

    private User getUserById(String id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new BizException(ExceptionType.USER_NOT_FOUND);
        }
        return user.get();
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
