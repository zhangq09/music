package com.study.music.dto;

import com.study.music.enums.MusicStatus;
import lombok.Data;

import java.util.Date;

@Data
public class MusicDto {
    private String id;

    private String name;

    private MusicStatus status;

    private String description;

    private Date createdTime;

    private Date updatedTime;
}
