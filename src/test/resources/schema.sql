create table if not exists character(
id SERIAL PRIMARY KEY,
name varchar(255),
status varchar(255),
species varchar(255),
type varchar(255),
gender varchar(255),
origin varchar(10000),
location varchar(10000),
image varchar(255),
episode varchar(10000),
url varchar(255),
created varchar(255));