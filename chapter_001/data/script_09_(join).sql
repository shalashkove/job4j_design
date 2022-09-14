create table departments(
    id serial primary key,
    name varchar(255)
);

create table employees(
    id serial primary key,
    name varchar(255),
    departments_id int references departments(id)
);

insert into departments(name) values ('management');
insert into departments(name) values ('accounting');
insert into departments(name) values ('prodaction');
insert into departments(name) values ('it');

insert into employees(name, departments_id) values ('Ivanov', 1);
insert into employees(name, departments_id) values ('Petrov', 2);
insert into employees(name, departments_id) values ('Sidorov', 3);
insert into employees(name, departments_id) values ('Kuznetcov', null);
insert into employees(name, departments_id) values ('Vavilov', 1);
insert into employees(name, departments_id) values ('Zaitcev', 3);

select *
from employees e
left join departments d
on e.departments_id = d.id;

select *
from employees e
right join departments d
on e.departments_id = d.id;

select *
from employees e
full join departments d
on e.departments_id = d.id;

select *
from employees e
cross join departments d;

select *
from departments d
left join employees e
on d.id = e.departments_id where e.departments_id is null;

select e.id, e.name, e.departments_id, d.id, d.name
from employees e
left join departments d
on e.departments_id = d.id;

select e.id, e.name, e.departments_id, d.id, d.name
from departments d
right join employees e
on e.departments_id = d.id;

create table teens(
    id serial primary key,
    name varchar(255),
    gender varchar(255)
);

insert into teens(name, gender) values ('Ivanov', 'man');
insert into teens(name, gender) values ('Sidorov', 'man');
insert into teens(name, gender) values ('Petrova', 'woman');
insert into teens(name, gender) values ('Kuznetcov', 'man');
insert into teens(name, gender) values ('Zaitceva', 'woman');

select *
from teens t
cross join teens tt
where t.gender != tt.gender
and t.gender = 'man';