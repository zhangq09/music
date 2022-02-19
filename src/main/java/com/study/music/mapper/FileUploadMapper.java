package com.study.music.mapper;

import com.study.music.dto.FileUploadDto;
import com.study.music.vo.FileUploadVo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface FileUploadMapper {
    FileUploadVo toVo(FileUploadDto fileUploadDto);
}
