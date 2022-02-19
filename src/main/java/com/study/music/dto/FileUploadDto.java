package com.study.music.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class FileUploadDto {
    private String secretId;

    private String secretKey;

    private String sessionToken;

    private String bucket;

    private String region;

    private long expiredTime;
}
