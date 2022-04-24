create table recruitment.resume(
     resume_id int primary key auto_increment,
     user_id   int not null ,
     user_name char(64) not null ,
     user_age char(64) not null ,
     user_sex char(64) not null ,
     user_phone char(64) not null ,
     user_email char(64) not null ,
     type char(32),
     introduction text,
     edu_experience text,
     experience text,
     award text,
     enable bool default true,
     foreign key (user_id) references recruitment.user(user_id)
)