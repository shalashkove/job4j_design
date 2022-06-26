create table type(
    id serial primary key,
    name varchar(255)
);

create table product(
    id serial primary key,
    name varchar(255),
    type_id int references type(id),
    expired_date timestamp without time zone,
    price float
);

insert into type (name) values ('СЫР'), ('МОЛОКО'), ('МЯСО'), ('ФРУКТЫ'), ('МОРЖЕННОЕ'), ('ХЛЕБ');
insert into product (name, type_id, expired_date, price) values
('Сыр плавленный', 1, '2022-07-21 12:00:00', 56.00),
('Сыр моцарелла', 1, '2022-05-31 12:00:00', 250.50),
('Кефир', 2, '2022-07-10 12:00:00', 54.00),
('Молоко', 2, '2022-06-01 12:00:00', 48.50),
('Яблоки', 4, '2022-09-30 12:00:00', 105.00),
('Груши', 4, '2022-09-30 12:00:00', 150.00),
('Эскимо', 5, '2022-09-08 15:55:55', 100.00),
('Мороженное пломбир', 5, '2022-12-01 12:00:00', 80.00),
('Мороженное мясо', 3, '2022-06-15 12:00:00', 500.00),
('Батон', 6, '2022-06-27 12:00:00', 42.50);

select *
from product as p
join type as t
on p.type_id = t.id
where t.name = 'СЫР';

select *
from product as p
join type as t
on p.type_id = t.id
where lower(p.name) like '%мороженное%';

select *
from product as p
join type as t
on p.type_id = t.id
where p.expired_date < current_date;

select name, price
from product
where price = (select max(price) from product);

select t.name as имя_типа, count(*) as количество
from type as t
join product as p
on t.id = p.type_id
group by t.name;

select *
from product as p
join type as t
on p.type_id = t.id
where t.name = 'СЫР' or t.name = 'МОЛОКО';

select t.name as имя_типа, count(*) as количество
from type as t
join product as p
on t.id = p.type_id
group by t.name
having count(*) < 10;

select p.name as product_name, t.name as product_type
from product as p
join type as t
on p.type_id = t.id;
