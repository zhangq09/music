package com.study.music.service;

import com.study.music.dto.FileDto;
import com.study.music.dto.FileRequest;
import com.study.music.dto.FileUploadDto;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface FileService {

    FileDto initUpload(FileRequest fileRequest) throws IOException;

    FileUploadDto getToken() throws IOException;

    void download(String id, HttpServletResponse response) throws IOException;
}
