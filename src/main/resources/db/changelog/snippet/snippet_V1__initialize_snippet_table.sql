create table pastebin.snippets (id varchar(255) not null,
                                creation_time timestamp(6),
                                expiration_time timestamp(6),
                                author varchar(255),
                                body varchar(255),
                                link varchar(255) unique,
                                primary key (id))