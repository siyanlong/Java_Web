drop database IF EXISTS siyl_msg;
create database siyl_msg;
use siyl_msg;
create table t_user(
	id int(10) primary key auto_increment,
	username varchar(100),
	password varchar(100),
	nickname varchar(100),
	status int(3),
	type int(3)
);

create table t_msg(
	id int(10) primary key auto_increment,
	title varchar(255),
	content text,
	post_date datetime,
	user_id int(10),
	CONSTRAINT FOREIGN KEY(user_id) REFERENCES t_user(id)
);

create table t_comment(
	id int(10) primary key auto_increment,
	content text,
	post_date datetime,
	user_id int(10),
	msg_id int(10),
	CONSTRAINT FOREIGN KEY(user_id) REFERENCES t_user(id),
	CONSTRAINT FOREIGN KEY(msg_id) REFERENCES t_msg(id)
);
GRANT ALL ON siyl.* to 'siyl'@'localhost' IDENTIFIED BY '123456';