insert into usr (id, username, password, email, active) values
(1, 'admin', '$2a$08$AuLMiIojO3jh1acNoj1djuGzr1Q0bgH0e0YwMOvmk9gnTPRyJEwAa', 'admin@admin.admin', true);
-- password = admin

insert into user_role (user_id, roles) values
(1, 'USER'), (1,  'ADMIN');
