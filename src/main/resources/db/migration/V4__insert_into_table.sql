INSERT INTO music.user (id, username, password, gender, nickname, locked, enabled, last_login_ip, last_login_time,
                        created_time, updated_time)
VALUES ('252qA15RSI01iCvgIEgOxO1kVSV',
        'admin',
        '$2a$10$XJqRr9SR0zgP2n7w1vlxxeC87xCxwalhOuPRikuqNgxkZ7c3EIXqy',
        'UNKNOWN',
        'admin', 0, 1, null, null,
        '2022-02-13 14:56:21.454000', '2022-02-13 14:56:21.455000');

INSERT INTO music.role (id, name, title, created_time, updated_time)
VALUES ('1', 'ROLE_USER', '普通用户', '2022-02-13 15:02:34', '2022-02-13 15:02:34');
INSERT INTO music.role (id, name, title, created_time, updated_time)
VALUES ('2', 'ROLE_USER_ADMIN', '会员用户', '2022-02-13 15:02:34', '2022-02-13 15:02:34');

INSERT INTO music.user_role (user_id, role_id)
VALUES ('252qA15RSI01iCvgIEgOxO1kVSV', '2');
