create table book(
    id serial primary key,
    name varchar(255),
	autor_id int references autor(id)
);

create table person(
    id serial primary key,
    name varchar(255)
);

create table book_person(
    id serial primary key,
    book_id int references book(id),
	person_id int references person(id)
);