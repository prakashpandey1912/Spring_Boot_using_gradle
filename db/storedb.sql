create table customer
(
username char(35) primary key,
password char(100) not null,
password_key char(50) not null,
email_id char(100) not null unique
);