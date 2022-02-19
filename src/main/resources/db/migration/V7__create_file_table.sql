CREATE TABLE file
(
    id VARCHAR(32) NOT NULL
        PRIMARY KEY COMMENT '歌曲ID',
    name VARCHAR(64) NOT NULL COMMENT '文件名',
    file_key varchar(32) not  NULL COMMENT 'key',
    ext varchar(8) not NULL comment '后缀',
    size int not null comment '大小',
    type VARCHAR(32) DEFAULT 'DRAFT' NOT NULL COMMENT '文件类型',
    storage VARCHAR(3) not null comment '存储方式',
    status VARCHAR(12) not null comment  '文件状态',
    path varchar(64) not null comment  '存储路径',
    created_time datetime(6) NOT NULL COMMENT '创建时间',
    updated_time datetime(6) NOT NULL COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '文件表';
