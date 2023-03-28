create table role
(
    id bigint not null auto_increment,
    name varchar(255),
    primary key (id)
);
create table user_roles
(
    user_id long not null,
    role_id long not null,
    primary key (user_id,role_id)
);
create table user
(
    id bigint not null auto_increment,
    email varchar(255),
    password varchar(255),
    primary key (id)
);
alter table user_roles
    add constraint user_roles_role foreign key (role_id) references role (id);
alter table user_roles
    add constraint user_roles_user foreign key (user_id) references user (id);