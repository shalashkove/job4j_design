create table autor(
    id serial primary key,
    name varchar(255)
);

create table book(
    id serial primary key,
    name varchar(255),
	autor_id int references autor(id)
);