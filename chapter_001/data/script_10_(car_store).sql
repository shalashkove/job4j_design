create table car_bodies(
    id serial primary key,
    name varchar(255)
);

create table car_engines(
    id serial primary key,
    name varchar(255)
);

create table car_transmissions(
    id serial primary key,
    name varchar(255)
);

create table cars(
    id serial primary key,
    name varchar(255),
    body_id int references car_bodies(id),
    engine_id int references car_engines(id),
    transmission_id int references car_transmissions(id)
);

insert into car_bodies(name) values ('седан'), ('хэтчбек'), ('пикап');
insert into car_engines(name) values ('бензиновый'), ('электро'), ('дизель');
insert into car_transmissions(name) values ('робот'), ('ручная'), ('автомат');
insert into cars(name, body_id, engine_id, transmission_id) values
('Ауди', 1, 3, 3),
('БМВ', 2, 1, 3),
('Мерседес', 1, 1, 2),
('Рено', null, 2, 2),
('Мазда', 2, null, 2),
('УАЗ', 1, 2, null),
('Лада', null, null, null);

select cs.id as id, cs.name as car_name, cb.name as body_name, ce.name as engine_name, ct.name transmission_name
from cars cs
left join car_bodies cb
on cs.body_id = cb.id
left join car_engines ce
on cs.engine_id = ce.id
left join car_transmissions ct
on cs.transmission_id = ct.id;

select cb.name
from car_bodies cb
left join cars cs
on cs.body_id = cb.id
where cs.body_id is null;

select ce.name
from car_engines ce
left join cars cs
on cs.engine_id = ce.id
where cs.engine_id is null;

select ct.name
from car_transmissions ct
left join cars cs
on cs.transmission_id = ct.id
where cs.transmission_id is null;