package com.study.music.dto;

import com.study.music.entity.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {

    private String id;

    private String username;

    private String nickname;

    private List<Role> roles;
}
