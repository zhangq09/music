package com.study.music.service.impl;

import com.study.music.dto.MusicCreateRequest;
import com.study.music.dto.MusicDto;
import com.study.music.dto.MusicUpdateRequest;
import com.study.music.entity.Music;
import com.study.music.enums.ExceptionType;
import com.study.music.enums.MusicStatus;
import com.study.music.exception.BizException;
import com.study.music.mapper.MusicMapper;
import com.study.music.repository.MusicRepository;
import com.study.music.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MusicServiceImpl implements MusicService {
    private MusicRepository musicRepository;

    private MusicMapper mapper;

    @Override
    public MusicDto create(MusicCreateRequest musicCreateRequest) {
        return mapper.toDto(musicRepository.save(mapper.createEntity(musicCreateRequest)));
    }

    @Override
    public MusicDto update(MusicUpdateRequest musicUpdateRequest, String id) {
        Music music = checkMusicById(id);
        return mapper.toDto(musicRepository.save(mapper.updateEntity(music, musicUpdateRequest)));
    }

    @Override
    public List<MusicDto> findByName(String name) {
        return musicRepository.findAllByName(name).stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public MusicDto publish(String id) {
        Music music = checkMusicById(id);
        return mapper.toDto(musicRepository.save(music.setStatus(MusicStatus.PUBLISHED)));
    }

    @Override
    public MusicDto close(String id) {
        Music music = checkMusicById(id);
        return mapper.toDto(musicRepository.save(music.setStatus(MusicStatus.CLOSED)));
    }

    private Music checkMusicById(String id) {
        Optional<Music> music = musicRepository.findById(id);
        if (!music.isPresent()) {
            throw new BizException(ExceptionType.NOT_FOUND_MUSIC);
        }
        return music.get();
    }

    @Autowired
    private void setMusicRepository(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }

    @Autowired
    private void setMapper(MusicMapper musicMapper) {
        this.mapper = musicMapper;
    }
}
