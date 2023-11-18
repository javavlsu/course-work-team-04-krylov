create table examination_cabinet_referral_time
(
    id  bigint not null auto_increment,
    examination_cabinet_id bigint,
    time time,
    primary key (id)
) engine=InnoDB;

alter table examination_cabinet_referral_time add constraint xamination_cabinet_referral_time_cabinet foreign key (examination_cabinet_id) references examination_cabinet (id);