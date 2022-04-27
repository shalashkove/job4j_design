create table painting_type(
    id serial primary key,
    name varchar(255)
);

create table picture(
    id serial primary key,
    name varchar(255),
    painting_type_id int references painting_type(id)
);

insert into painting_type(name) values ('landscape');
insert into painting_type(name) values ('portrait');
insert into painting_type(name) values ('still life');

insert into picture(name, painting_type_id) values('Шторм', 2);
insert into picture(name, painting_type_id) values('Mona Lisa', 1);
insert into picture(name, painting_type_id) values('Черный квадрат', 3);
insert into picture(name, painting_type_id) values('Star Night', 2);
insert into picture(name, painting_type_id) values('Неизвестная', 1);
insert into picture(name) values('тайная вечеря');

select *
from picture as p
join painting_type as t
on p.painting_type_id = t.id;

select p.name, t.name
from picture as p
join painting_type as t
on p.painting_type_id = t.id;

select p.name as Имя_картины, t.name as Тип_картины
from picture as p
join painting_type as t
on p.painting_type_id = t.id;