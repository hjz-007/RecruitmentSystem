create table recruitment.recruitment_info(
     recruitment_id int primary key auto_increment,
     company_id int,
     company_name char(64),
     job_name char(64),
     salary char(64),
     address char(64),
     type    char(32),
     direction char(32),
     requirement char(64),
     responsibility char(64),
     foreign key (company_id) references recruitment.company(company_id)
)