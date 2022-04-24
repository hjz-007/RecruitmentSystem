create table recruitment.recruitment_info(
     recruitment_id int primary key auto_increment,
     company_id int NOT NULL ,
     company_name char(64) NOT NULL ,
     job_name char(64) NOT NULL ,
     education char(32),
     salary int,
     address char(64),
     type char(64),
     introduction text,
     requirement text,
     responsibility text,
     is_enable boolean default true,
     create_time DATETIME NOT NULL ,
     foreign key (company_id) references recruitment.company(company_id)
)