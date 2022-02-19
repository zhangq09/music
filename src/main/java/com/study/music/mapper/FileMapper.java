package com.study.music.mapper;

import com.study.music.dto.FileDto;
import com.study.music.dto.FileRequest;
import com.study.music.entity.File;
import com.study.music.vo.FileVo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface FileMapper {
    File createEntity(FileRequest fileRequest);

    FileDto toDto(File file);

    FileVo toVo(FileDto fileDto);
}
