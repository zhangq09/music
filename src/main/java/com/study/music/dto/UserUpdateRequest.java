package com.study.music.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserUpdateRequest {

    @NotBlank(message = "用户名不能为空")
    @Size(min = 4, max = 12, message = "用户名长度应该在4个字符到12个字符之间")
    private String username;
    
    private String nickname;
    private String gender;

}
