create table books(
	id serial primary key,
	title varchar(100),
	description text,
	pages integer
);
insert into books(title, description, pages) values('Энциклопедия', 'Очень полезная книга', 1250);
update books set pages = 2500;
delete from books;
