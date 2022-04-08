create table recruitment.resume(
     resume_id int primary key auto_increment,
     user_id   int,
     user_iame char(64),
     user_age char(64),
     user_sex char(64),
     user_phone char(64),
     user_email char(64),
     nation   char(32),
     education char(32),
     hometown  char(64),
     introduction text,
     experience text,
     award text,
     foreign key (user_id) references recruitment.user(user_id)
)