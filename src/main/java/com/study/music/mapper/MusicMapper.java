package com.study.music.mapper;

import com.study.music.dto.MusicCreateRequest;
import com.study.music.dto.MusicDto;
import com.study.music.dto.MusicUpdateRequest;
import com.study.music.entity.Music;
import com.study.music.vo.MusicVo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface MusicMapper {
    Music createEntity(MusicCreateRequest musicCreateRequest);

    MusicDto toDto(Music music);

    MusicVo toVo(MusicDto musicDto);

    Music updateEntity(@MappingTarget Music music, MusicUpdateRequest musicUpdateRequest);
}
