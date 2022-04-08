create table recruitment.user(
    user_id int PRIMARY KEY auto_increment,
    user_name char(32),
    user_phone char(64),
    user_email char(64) NOT NULL unique ,
    user_password char(64) NOT NULL
);
