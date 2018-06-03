--  使用 h2数据库， values("") 的值不能使用双引号
-- sys_user
INSERT INTO sys_user (id, user_name, sex, email) VALUES (1, 'admin', '男', NULL);
INSERT INTO sys_user (id, user_name, sex, email) VALUES (2, 'xiaofang', '女', 'xx@xx.com');
INSERT INTO sys_user (id, user_name, sex, email) VALUES (3, 'lisi', '男', NULL);
INSERT INTO sys_user (id, user_name, sex, email) VALUES (4, 'wangwu', '女', NULL);
INSERT INTO sys_user (id, user_name, sex, email) VALUES (5, 'zhaoliu', '男', NULL);