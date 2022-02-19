package com.study.music.dto;

import com.study.music.enums.FileStatus;
import com.study.music.enums.FileType;
import com.study.music.enums.Storage;
import lombok.Data;

import java.util.Date;

@Data
public class FileDto {
    private String id;

    private String name;

    private String fileKey;

    private String ext;

    private Integer size;

    private FileType type;

    private Storage storage;

    private String path;

    private FileStatus status;

    private Date createdTime;

    private Date updatedTime;
}
