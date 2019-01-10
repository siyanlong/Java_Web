drop database if exists siyl_shop;
create database siyl_shop;
use siyl_shop;
create table t_user (
	id int(10) primary key auto_increment,
	username varchar(100),
	password varchar(100),
	nickname varchar(100)
);
GRANT ALL ON siyl_shop.* to 'siyl'@'localhost' IDENTIFIED BY '19880822'