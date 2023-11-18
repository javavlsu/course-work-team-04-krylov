create table analysis_cabinet
(
    id  bigint not null auto_increment,
    name varchar(255),
    number varchar(255),
    schedule varchar(255),
    primary key (id)
) engine=InnoDB;
create table examination_cabinet
(
    id  bigint not null auto_increment,
    name varchar(255),
    number varchar(255),
    schedule varchar(255),
    primary key (id)
) engine=InnoDB;



alter table analysis_referral drop column cabinet_num;
alter table analysis_referral drop column laboratory;
alter table analysis_referral drop column schedule;

alter table examination_referral drop column cabinet_num;
alter table examination_referral drop column schedule;
alter table examination_referral drop column type;

alter table analysis_referral add column cabinet_id bigint;
alter table analysis_referral add constraint analysis_referral_cabinet foreign key (cabinet_id) references analysis_cabinet (id);

alter table examination_referral add column cabinet_id bigint;
alter table examination_referral add constraint examination_referral_cabinet foreign key (cabinet_id) references examination_cabinet (id);

alter table assistant add column cabinet_id bigint;
alter table assistant add constraint assistant_cabinet foreign key (cabinet_id) references analysis_cabinet (id);

alter table functional_diagnostics_doctor add column cabinet_id bigint;
alter table functional_diagnostics_doctor add constraint functional_diagnostics_doctor_cabinet foreign key (cabinet_id) references examination_cabinet (id);
