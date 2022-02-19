package com.study.music.repository;

import com.study.music.entity.Music;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MusicRepository extends JpaRepository<Music, String> {

    Optional<Music> findById(String id);

    List<Music> findAllByName(String name);

    List<Music> findAll();
}
