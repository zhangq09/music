package com.study.music.entity;

import com.study.music.enums.MusicStatus;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Data
@Accessors(chain = true)
public class Music extends AbstractEntity {

    private String name;

    @Enumerated(value = EnumType.STRING)
    private MusicStatus status = MusicStatus.DRAFT;

    private String description;
}
