package com.study.music.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.study.music.enums.MusicStatus;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

@Data
public class MusicVo {
    private String id;

    private String name;

    @Enumerated(EnumType.STRING)
    private MusicStatus status;

    private String description;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyyMMddHHmmss")
    private Date createdTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyyMMddHHmmss")
    private Date updatedTime;
}
