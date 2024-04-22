CREATE DATABASE chat ENCODING 'UTF-8';

create table if not exists users (
                                      id serial primary key,
                                      login varchar not null,
                                      password varchar not null
);

create table if not exists chatrooms (
                                        id serial primary key,
                                        name varchar not null,
                                        owner bigint not null,
                                        constraint fk_chatroom_owner foreign key (owner) references "user"(id)
);

create table if not exists messages (
                                         id serial primary key,
                                         author bigint not null,
                                         room bigint not null,
                                         text varchar not null,
                                         datetime timestamp not null,
                                         constraint fk_message_author foreign key (author) references "user"(id),
                                         constraint fk_message_room foreign key (room) references chatroom(id)
);
