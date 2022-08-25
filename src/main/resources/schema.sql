DROP TABLE if exists letter_table;
DROP TABLE if exists entry_table;

CREATE TABLE letter_table(
id int NOT NULL AUTO_INCREMENT,
name varchar(64),
address varchar(255),
PRIMARY KEY(id)
);

CREATE TABLE entry_table(
	id int NOT NULL AUTO_INCREMENT,
	address varchar(255) NOT NULL,
	name varchar(255) NOT NULL,
	yomi varchar(255) NOT NULL,
	PRIMARY KEY (id)
);