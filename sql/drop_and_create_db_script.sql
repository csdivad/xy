
    drop table if exists users;

    create table users (
        username varchar(45) not null,
        password varchar(45) not null,
        enabled bit,
        primary key (username)
    );
