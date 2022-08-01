delete from message;

insert into message (id, text, tag, user_id) values
(1, 'first', 'first-tag', 1),
(2, 'second', 'second-tag', 1),
(3, 'third', 'third-tag', 1),
(4, 'fourth', 'fourth-tag', 1);

alter table hibernate_sequence restart with 10;
