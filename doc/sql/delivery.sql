create table recruitment.delivery(
     resume_id int primary key auto_increment,
     user_id int,
     recruitment_id int,
     status char(64),
     create_time long,
     foreign key (resume_id) references recruitment.resume(resume_id),
     foreign key (recruitment_id) references recruitment.recruitment_info(recruitment_id)
)