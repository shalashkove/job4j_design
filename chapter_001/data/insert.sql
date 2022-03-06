insert into role(role_name) values('Администратор');
insert into role(role_name) values('Пользователь');
insert into role(role_name) values('Оператор');

insert into users(name_user, role_id) values('Василий', 1);
insert into users(name_user, role_id) values('Михаил', 2);
insert into users(name_user, role_id) values('Иван', 3);

insert into rules(rules_name) values('Чтение');
insert into rules(rules_name) values('Запись');
insert into rules(rules_name) values('Чтение и запись');

insert into role_rules(role_id, rules_id) values(1, 3);
insert into role_rules(role_id, rules_id) values(2, 1);
insert into role_rules(role_id, rules_id) values(3, 2);

insert into state(state_name) values('Оформление');
insert into state(state_name) values('Оплата');
insert into state(state_name) values('Отправка');

insert into category(category_name) values('Обычная');
insert into category(category_name) values('Срочная');
insert into category(category_name) values('VIP');

insert into item(item_name, users_id, category_id, state_id) values('Заявка № 1', 3, 1, 1);
insert into item(item_name, users_id, category_id, state_id) values('Заявка № 2', 2, 2, 2);
insert into item(item_name, users_id, category_id, state_id) values('Заявка № 3', 1, 3, 3);

insert into comments(comment_text, item_id) values('Повышенная срочность', 3);
insert into comments(comment_text, item_id) values('Без комментариев', 1);
insert into comments(comment_text, item_id) values('Доставьть побыстрее пожалуйста', 2);

insert into attachs(attach_name, item_id) values('c:\\attaches\attach1.dat', 1);
insert into attachs(attach_name, item_id) values('c:\\attaches\attach2.dat', 1);
insert into attachs(attach_name, item_id) values('c:\\attaches\attach3.dat', 2);
