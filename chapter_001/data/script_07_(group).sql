create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values ('Телефон', 12000.00), ('Наушники', 500.50), ('Гарнитура', 2500.40);
insert into people(name) values ('Вася'), ('Петя'), ('Миша');
insert into devices_people(device_id, people_id) values (1, 1), (2, 1), (3, 1);
insert into devices_people(device_id, people_id) values (1, 2), (2, 2);
insert into devices_people(device_id, people_id) values (2, 3);

select avg(price) from devices;

select p.name, avg(d.price)
from people as p join devices_people dp
on p.id = dp.people_id
join devices d
on dp.device_id = d.id
group by p.name;

select p.name, avg(d.price)
from people as p join devices_people dp
on p.id = dp.people_id
join devices d
on dp.device_id = d.id
group by p.name
having avg(d.price) > 5000;
