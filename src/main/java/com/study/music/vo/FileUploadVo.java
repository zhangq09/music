package com.study.music.vo;

import lombok.Data;

@Data
public class FileUploadVo {
    private String secretId;

    private String secretKey;

    private String sessionToken;

    private String bucket;

    private String region;
    //单位秒
    private long expiredTime;
}
