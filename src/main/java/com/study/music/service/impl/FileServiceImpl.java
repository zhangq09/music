package com.study.music.service.impl;

import com.study.music.dto.FileDto;
import com.study.music.dto.FileRequest;
import com.study.music.dto.FileUploadDto;
import com.study.music.entity.File;
import com.study.music.enums.ExceptionType;
import com.study.music.enums.Storage;
import com.study.music.exception.BizException;
import com.study.music.mapper.FileMapper;
import com.study.music.repository.FileRepository;
import com.study.music.service.FileService;
import com.study.music.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Service
public class FileServiceImpl implements FileService {

    private Map<String, StorageService> storageServices;

    private FileRepository repository;

    private FileMapper mapper;

    @Override
    @Transactional
    public FileDto initUpload(FileRequest fileRequest) throws IOException {
        // 创建File实体
        File file = repository.save(mapper.createEntity(fileRequest));
        return mapper.toDto(file);
    }

    @Override
    public FileUploadDto getToken() throws IOException {
        return storageServices.get(getDefaultStorage().name()).initFileUpload();
    }

    @Override
    public void download(String id, HttpServletResponse response) {
        Optional<File> file = repository.findById(id);
        if (!file.isPresent()) {
            throw new BizException(ExceptionType.NOT_FOUND_FILE);
        }

        storageServices.get(getDefaultStorage().name()).download(file.get().getFileKey(), response);
    }

    // Todo: 后台设置当前Storage
    private Storage getDefaultStorage() {
        return Storage.COS;
    }


    @Autowired
    public void setStorageServices(Map<String, StorageService> storageServices) {
        this.storageServices = storageServices;
    }

    @Autowired
    public void setRepository(FileRepository repository) {
        this.repository = repository;
    }

    @Autowired
    public void setMapper(FileMapper mapper) {
        this.mapper = mapper;
    }
}
