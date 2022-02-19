package com.study.music.entity;

import com.study.music.enums.FileStatus;
import com.study.music.enums.FileType;
import com.study.music.enums.Storage;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Data
public class File extends AbstractEntity {

    //名称
    private String name;

    //key值 唯一且加密
    private String fileKey;

    //后缀
    private String ext;

    private Integer size;

    private String path;

    @Enumerated(EnumType.STRING)
    private FileType type = FileType.AUDIO;

    @Enumerated(EnumType.STRING)
    private Storage storage = Storage.COS;

    @Enumerated(EnumType.STRING)
    private FileStatus status = FileStatus.UPLOADING;
}
