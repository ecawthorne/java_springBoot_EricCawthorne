CREATE TABLE IF NOT exists cat
(
	id int IDENTITY NOT NULL PRIMARY KEY,
	name varchar(12) NOT NULL,
	type varchar(12) NOT null
);