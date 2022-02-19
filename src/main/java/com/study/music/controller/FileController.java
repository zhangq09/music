package com.study.music.controller;

import com.study.music.dto.FileRequest;
import com.study.music.mapper.FileMapper;
import com.study.music.service.FileService;
import com.study.music.vo.FileVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/file")
public class FileController {

    private FileService fileService;

    private FileMapper fileMapper;

    @PostMapping("/upload_init")
    public FileVo initUpload(@Validated @RequestBody FileRequest fileRequest) throws IOException {
        return fileMapper.toVo(fileService.initUpload(fileRequest));
    }

    @GetMapping("/download/{id}")
    public void download(@PathVariable("id") String id, HttpServletResponse response) throws IOException {
        fileService.download(id, response);
    }

    @Autowired
    private void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    @Autowired
    private void setFileMapper(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }
}
