package com.study.music.service;

import com.study.music.dto.MusicCreateRequest;
import com.study.music.dto.MusicDto;
import com.study.music.dto.MusicUpdateRequest;

import java.util.List;

public interface MusicService {
    MusicDto create(MusicCreateRequest musicCreateRequest);

    MusicDto update(MusicUpdateRequest musicUpdateRequest, String id);


    List<MusicDto> findByName(String name);

    MusicDto publish(String id);

    MusicDto close(String id);
}
