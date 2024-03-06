create table pastebin.users (user_id bigserial not null,
                             email varchar(255) not null unique,
                             lastname varchar(255) not null,
                             password varchar(255) not null,
                             role varchar(255),
                             username varchar(255) not null,
                             primary key (user_id))