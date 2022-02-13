package com.study.music.controller;

import com.study.music.dto.UserCreateDto;
import com.study.music.dto.UserUpdateRequest;
import com.study.music.mapper.UserMapper;
import com.study.music.service.UserService;
import com.study.music.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/users")
@CrossOrigin
@Api(tags = "用户")
public class UserController {

    UserService userService;

    UserMapper userMapper;

    @GetMapping()
    @ApiOperation("search:用户检索查询并分页")
    @RolesAllowed("ROLE_USER_ADMIN")
    Page<UserVo> search(@PageableDefault(sort = {"createdTime"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return userService.search(pageable).map(userMapper::toVo);
    }

    @PostMapping()
    @ApiOperation("create:创建用户")
    @RolesAllowed("ROLE_USER_ADMIN")
    UserVo create(@Validated @RequestBody UserCreateDto userCreateDto) {
        return userMapper.toVo(userService.create(userCreateDto));
    }

    @GetMapping("/{id}")
    @ApiOperation("get:通过id查找用户")
    UserVo get(@PathVariable String id) {
        return userMapper.toVo(userService.get(id));
    }

    @PutMapping("/{id}")
    @ApiOperation("update:通过id和传入的用户信息更新用户")
    @RolesAllowed("ROLE_USER_ADMIN")
    UserVo update(@PathVariable String id, @Validated @RequestBody UserUpdateRequest userUpdateRequest) {
        return userMapper.toVo(userService.update(id, userUpdateRequest));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("delete:通过id删除用户")
    @RolesAllowed("ROLE_USER_ADMIN")
    void delete(@PathVariable String id) {
        userService.delete(id);
    }

    @GetMapping("/current")
    UserVo current() {
        return userMapper.toVo(userService.getCurrentUser());
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
