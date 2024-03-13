create table pastebin.users (user_id bigserial not null,
                             username varchar(255) not null,
                             lastname varchar(255) not null,
                             email varchar(255) not null unique,
                             password varchar(255) not null,
                             role varchar(255),
                             primary key (user_id))