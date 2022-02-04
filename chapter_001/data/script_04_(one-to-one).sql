create table libraryCard(
    id serial primary key,
    number int
);

create table person(
    id serial primary key,
    name varchar(255)
);

create table libraryCard_person(
    id serial primary key,
    libraryCard_id int references libraryCard(id) unique,
	person_id int references person(id) unique
);