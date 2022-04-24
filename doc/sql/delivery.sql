create table recruitment.delivery(
     delivery_id int primary key auto_increment,
     company_id int NOT NULL ,
     resume_id int NOT NULL ,
     recruitment_id int NOT NULL ,
     status char(64),
     create_time Datetime not null,
     foreign key (resume_id) references recruitment.resume(resume_id),
     foreign key (recruitment_id) references recruitment.recruitment_info(recruitment_id)
)