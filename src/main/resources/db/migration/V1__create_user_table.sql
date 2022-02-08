CREATE TABLE user
(
    id VARCHAR(32) NOT NULL PRIMARY KEY COMMENT '用户ID',
    username VARCHAR(64) NOT NULL COMMENT '用户名',
    password  VARCHAR(64) NOT NULL COMMENT '加密后的密码',
    gender VARCHAR(255) NOT NULL COMMENT '性别',
    nickname varchar(64) null comment '用户昵称',
    locked tinyint(1) default 0 not null comment '是否锁定，1-是，0-否',
    enabled tinyint(1) default 1 not null comment '是否可用，1-是，0-否',
    last_login_ip varchar(64) null comment '最后登录ip',
    last_login_time datetime(6) null comment '最后登录时间',
    created_time datetime(6) null comment '创建时间',
    updated_time datetime(6) null comment '更新时间',
    constraint uk_user_username
        unique (username)
) engine=InnoDB default charset=utf8mb4 collate =utf8mb4_bin comment '用户表';