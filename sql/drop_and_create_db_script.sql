
    alter table account 
        drop 
        foreign key FK_gex1lmaqpg0ir5g1f5eftyaa1;

    alter table user_details 
        drop 
        foreign key FK_qqadnciq8gixe1qmxd0rj9cyk;

    alter table user_roles 
        drop 
        foreign key FK_9ry105icat2dux14oyixybw9l;

    drop table if exists account;

    drop table if exists user_details;

    drop table if exists user_roles;

    drop table if exists users;

    create table account (
        account_id integer not null auto_increment,
        balance integer not null,
        username varchar(45) not null,
        primary key (account_id)
    );

    create table user_details (
        address varchar(200) not null,
        date_of_birth date not null,
        email varchar(255) not null,
        family_name varchar(35) not null,
        given_name varchar(35) not null,
        phone varchar(30) not null,
        username varchar(45) not null,
        primary key (username)
    );

    create table user_roles (
        user_role_id integer not null auto_increment,
        role varchar(45) not null,
        username varchar(45) not null,
        primary key (user_role_id)
    );

    create table users (
        username varchar(45) not null,
        enabled bit not null,
        last_login datetime,
        password varchar(60) not null,
        primary key (username)
    );

    alter table user_details 
        add constraint UK_4d9rdl7d52k8x3etihxlaujvh  unique (email);

    alter table user_roles 
        add constraint UK_stlxfukw77ov5w1wo1tm3omca  unique (role, username);

    alter table account 
        add constraint FK_gex1lmaqpg0ir5g1f5eftyaa1 
        foreign key (username) 
        references users (username);

    alter table user_details 
        add constraint FK_qqadnciq8gixe1qmxd0rj9cyk 
        foreign key (username) 
        references users (username);

    alter table user_roles 
        add constraint FK_9ry105icat2dux14oyixybw9l 
        foreign key (username) 
        references users (username);
