package com.study.music.controller;

import com.study.music.dto.MusicCreateRequest;
import com.study.music.dto.MusicUpdateRequest;
import com.study.music.mapper.MusicMapper;
import com.study.music.service.MusicService;
import com.study.music.vo.MusicVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/music")
public class MusicController {

    private MusicService musicService;

    private MusicMapper mapper;

    @PostMapping()
    public MusicVo create(@RequestBody MusicCreateRequest musicCreateRequest) {
        return mapper.toVo(musicService.create(musicCreateRequest));
    }

    @PutMapping("/{id}")
    @RolesAllowed("ROLE_USER_ADMIN")
    public MusicVo update(@RequestBody MusicUpdateRequest musicUpdateRequest, @PathVariable("id") String id) {
        return mapper.toVo(musicService.update(musicUpdateRequest, id));
    }

    @GetMapping("/{name}")
    public List<MusicVo> getById(@PathVariable("name") String name) {
        return musicService.findByName(name).stream().map(mapper::toVo).collect(Collectors.toList());
    }

    @GetMapping("/publish/{id}")
    @RolesAllowed("ROLE_USER_ADMIN")
    public MusicVo publish(@PathVariable("id") String id) {
        return mapper.toVo(musicService.publish(id));
    }

    @GetMapping("/close/{id}")
    @RolesAllowed("ROLE_USER_ADMIN")
    public MusicVo close(@PathVariable("id") String id) {
        return mapper.toVo(musicService.close(id));
    }

    @Autowired
    private void setMusicService(MusicService musicService) {
        this.musicService = musicService;
    }

    @Autowired
    private void setMapper(MusicMapper musicMapper) {
        this.mapper = musicMapper;
    }
}
