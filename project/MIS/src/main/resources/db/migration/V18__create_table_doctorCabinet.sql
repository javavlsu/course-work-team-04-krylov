create table doctor_cabinet
(
    id  bigint not null auto_increment,
    name varchar(255),
    number varchar(255),
    schedule varchar(255),
    primary key (id)
) engine=InnoDB;

alter table doctor add column cabinet bigint;
alter table doctor add constraint doctor_cabinet foreign key (cabinet) references doctor_cabinet (id);