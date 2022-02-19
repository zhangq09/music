package com.study.music.service;

import com.study.music.dto.FileUploadDto;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface StorageService {
    FileUploadDto initFileUpload() throws IOException;

    void download(String key, HttpServletResponse response) throws IOException;
}
