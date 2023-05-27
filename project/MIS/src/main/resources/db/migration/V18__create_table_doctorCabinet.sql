create table doctor_cabinet
(
    id  bigint not null auto_increment,
    name varchar(255),
    number varchar(255),
    schedule varchar(255),
    primary key (id)
) engine=InnoDB;

alter table doctor add column cabinet_id bigint;
alter table doctor add constraint doctor_cabinet foreign key (cabinet_id) references doctor_cabinet (id);