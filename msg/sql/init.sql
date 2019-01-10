drop database if exists siyl_msg;
create database siyl_msg;
use siyl_msg;
create table t_user (
	id int(10) primary key auto_increment,
	username varchar(100),
	password varchar(100),
	nickname varchar(100)
);
GRANT ALL ON siyl_msg.* to 'siyl'@'localhost' IDENTIFIED BY '19880822'