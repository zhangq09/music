package com.study.music.service;

import com.study.music.dto.TokenCreateRequest;
import com.study.music.dto.UserCreateDto;
import com.study.music.dto.UserDto;
import com.study.music.dto.UserUpdateRequest;
import com.study.music.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<UserDto> list();

    UserDto create(UserCreateDto userCreateDto);

    @Override
    User loadUserByUsername(String username) throws UsernameNotFoundException;

    UserDto get(String id);

    UserDto update(String id, UserUpdateRequest userUpdateRequest);

    Page<UserDto> search(Pageable pageable);

    void delete(String id);

    String createToken(TokenCreateRequest tokenCreateRequest);

    UserDto getCurrentUser();
}
