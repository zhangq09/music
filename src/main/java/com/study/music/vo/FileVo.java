package com.study.music.vo;

import com.study.music.enums.FileStatus;
import com.study.music.enums.FileType;
import lombok.Data;

import java.util.Date;

@Data
public class FileVo {

    private String name;

    private String fileKey;

    private String ext;

    private Integer size;

    private FileType type;

    private FileStatus status;

    private Date createdTime;

    private Date updatedTime;
}
