create table hibernate_sequence (
    next_val bigint
) engine=InnoDB;

insert into hibernate_sequence values (1);

create table message (
    id bigint not null,
    text varchar(2048) not null,
    tag varchar(255),
    filename varchar(255),
    user_id bigint,
    primary key (id)
) engine=InnoDB;

create table message_likes (
    message_id bigint not null,
    user_id bigint not null,
    primary key (message_id, user_id)
) engine=InnoDB;

create table user_role (
    user_id bigint not null,
    roles varchar(255)
) engine=InnoDB;

create table user_subscriptions (
    subscriber_id bigint not null,
    channel_id bigint not null,
    primary key (channel_id, subscriber_id)
) engine=InnoDB;

create table usr (
    id bigint not null,
    username varchar(255) not null,
    password varchar(255) not null,
    email varchar(255) not null,
    active bit not null,
    primary key (id)
) engine=InnoDB;

alter table message
    add constraint message_user_fk
        foreign key (user_id) references usr (id);

alter table message_likes
    add constraint message_likes_user_fk
        foreign key (user_id) references usr (id);

alter table message_likes
    add constraint message_likes_message_fk
        foreign key (message_id) references message (id);

alter table user_role
    add constraint user_role_user_fk
        foreign key (user_id) references usr (id);

alter table user_subscriptions
    add constraint user_subscriptions_channel_fk
        foreign key (channel_id) references usr (id);

alter table user_subscriptions
    add constraint user_subscriptions_subscriber_fk
        foreign key (subscriber_id) references usr (id);
