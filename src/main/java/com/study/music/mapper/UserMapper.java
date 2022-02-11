package com.study.music.mapper;

import com.study.music.dto.UserCreateDto;
import com.study.music.dto.UserDto;
import com.study.music.dto.UserUpdateRequest;
import com.study.music.entity.User;
import com.study.music.vo.UserVo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {

    UserDto toDto(User user);

    UserVo toVo(UserDto userDto);

    User createEntity(UserCreateDto userCreateDto);

    User updateEntity(@MappingTarget User user, UserUpdateRequest userUpdateRequest);
}
