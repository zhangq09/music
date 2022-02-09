package com.study.music.service;

import com.study.music.dto.UserCreateDto;
import com.study.music.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<UserDto> list();

    UserDto create(UserCreateDto userCreateDto);

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
