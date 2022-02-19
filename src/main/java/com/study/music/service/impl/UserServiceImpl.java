package com.study.music.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.study.music.config.SecurityConfig;
import com.study.music.dto.TokenCreateRequest;
import com.study.music.dto.UserCreateRequest;
import com.study.music.dto.UserDto;
import com.study.music.dto.UserUpdateRequest;
import com.study.music.entity.User;
import com.study.music.enums.ExceptionType;
import com.study.music.enums.Gender;
import com.study.music.exception.BizException;
import com.study.music.mapper.UserMapper;
import com.study.music.repository.RoleRepository;
import com.study.music.repository.UserRepository;
import com.study.music.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    RoleRepository roleRepository;

    UserMapper userMapper;

    PasswordEncoder passwordEncoder;

    @Override
    public List<UserDto> list() {
        return userRepository.findAll()
                .stream().map(userMapper::toDto).collect(Collectors.toList());
    }


    @Override
    public UserDto create(UserCreateRequest userCreateRequest) {
        checkUsername(userCreateRequest.getUsername());
        User user = userRepository.save(userCreateRequestMapper(userCreateRequest));
        return userMapper.toDto(user);
    }

    private User userCreateRequestMapper(UserCreateRequest userCreateRequest) {
        if (userCreateRequest == null) {
            return null;
        }
        User user = new User();
        user.setUsername(userCreateRequest.getUsername());
        user.setNickname(userCreateRequest.getNickname());
        user.setPassword(passwordEncoder.encode(userCreateRequest.getPassword()));
        if (userCreateRequest.getGender() != null) {
            user.setGender(Enum.valueOf(Gender.class, userCreateRequest.getGender()));
        }
        user.setRoles(userCreateRequest.getRoles().stream().map(roleRepository::findByName).collect(Collectors.toList()));
        return user;
    }

    @Override
    public User loadUserByUsername(String username) {
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

    public String createToken(TokenCreateRequest tokenCreateRequest) {
        User user = loadUserByUsername(tokenCreateRequest.getUsername());
        if (!passwordEncoder.matches(tokenCreateRequest.getPassword(), user.getPassword())) {
            throw new BizException(ExceptionType.USER_PASSWORD_NOT_MATCH);
        }
        if (!user.getEnabled()) {
            throw new BizException(ExceptionType.USER_NOT_ENABLE);
        }
        if (!user.isAccountNonExpired()) {
            throw new BizException(ExceptionType.USER_LOCKED);
        }
        //通过验证
        return getToken(user);
    }

    @Override
    public UserDto getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = loadUserByUsername(authentication.getName());
        return userMapper.toDto(user);
    }

    private String getToken(User user) {
        return JWT.create().withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SecurityConfig.SECRET.getBytes()));
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
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
