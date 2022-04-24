create table recruitment.company(
    company_id int primary key auto_increment,
    company_name char(64),
    address char(64),
    achievement text,
    company_info text,
    company_phone char(64),
    company_email char(64) not null unique ,
    company_password char(64) not null
)