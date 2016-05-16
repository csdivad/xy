
    drop table if exists users;

    create table users (
        username varchar(45) not null,
        enabled bit,
        password varchar(45) not null,
        primary key (username)
    );
