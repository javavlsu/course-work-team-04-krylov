create table analysis
(
    id           bigint not null auto_increment,
    date         date,
    report       varchar(255),
    type         varchar(255),
    assistant_id bigint,
    patient_id   bigint,
    primary key (id)
) engine=InnoDB;
create table analysis_referral
(
    id               bigint not null auto_increment,
    cabinet_num      varchar(255),
    date_of_taking   date,
    laboratory       varchar(255),
    schedule         varchar(255),
    status           varchar(255),
    what_to_research varchar(255),
    assistant_id     bigint,
    diagnosis_id     varchar(255),
    doctor_id        bigint,
    patient_id       bigint,
    primary key (id)
) engine=InnoDB;
create table assistant
(
    id          bigint not null auto_increment,
    birth_date  date,
    first_name  varchar(255),
    last_name   varchar(255),
    middle_name varchar(255),
    primary key (id)
) engine=InnoDB;
create table diagnosis
(
    id          varchar(255) not null,
    description varchar(255),
    primary key (id)
) engine=InnoDB;
create table doctor
(
    id          bigint not null auto_increment,
    birth_date  date,
    category    varchar(255),
    degree      varchar(255),
    first_name  varchar(255),
    last_name   varchar(255),
    middle_name varchar(255),
    speciality  varchar(255),
    primary key (id)
) engine=InnoDB;
create table doctor_appointment
(
    id         bigint not null auto_increment,
    cabinet_id varchar(255),
    date_time  date,
    status     varchar(255),
    doctor_id  bigint,
    patient_id bigint,
    primary key (id)
) engine=InnoDB;
create table doctor_referral
(
    id                bigint not null auto_increment,
    cabinet_num       varchar(255),
    date_of_taking    date,
    schedule          varchar(255),
    status            varchar(255),
    what_to_research  varchar(255),
    diagnosis_id      varchar(255),
    doctor_initial_id bigint,
    doctor_target_id  bigint,
    patient_id        bigint,
    primary key (id)
) engine=InnoDB;
create table examination
(
    id                               bigint not null auto_increment,
    report                           varchar(255),
    type                             varchar(255),
    functional_diagnostics_doctor_id bigint,
    patient_id                       bigint,
    primary key (id)
) engine=InnoDB;
create table examination_referral
(
    id                               bigint not null auto_increment,
    cabinet_num                      varchar(255),
    date_of_taking                   date,
    schedule                         varchar(255),
    status                           varchar(255),
    type                             varchar(255),
    what_to_research                 varchar(255),
    diagnosis_id                     varchar(255),
    doctor_id                        bigint,
    functional_diagnostics_doctor_id bigint,
    patient_id                       bigint,
    primary key (id)
) engine=InnoDB;
create table functional_diagnostics_doctor
(
    id          bigint not null auto_increment,
    birth_date  date,
    first_name  varchar(255),
    last_name   varchar(255),
    middle_name varchar(255),
    primary key (id)
) engine=InnoDB;
create table inspection
(
    id           bigint not null auto_increment,
    complaint    varchar(255),
    date         date,
    recipe       varchar(255),
    type         varchar(255),
    diagnosis_id varchar(255),
    doctor_id    bigint,
    patient_id   bigint,
    primary key (id)
) engine=InnoDB;
create table patient
(
    id             bigint  not null auto_increment,
    birth_date     date,
    first_name     varchar(255),
    home_address   varchar(255),
    last_name      varchar(255),
    middle_name    varchar(255),
    poils_company  varchar(255),
    polis_end_date date,
    polisid        integer not null,
    snils_number   integer not null,
    work_place     varchar(255),
    work_position  varchar(255),
    primary key (id)
) engine=InnoDB;
create table receptionist
(
    id          bigint not null auto_increment,
    birth_date  date,
    first_name  varchar(255),
    last_name   varchar(255),
    middle_name varchar(255),
    primary key (id)
) engine=InnoDB;
alter table analysis
    add constraint FK5xk4i0a2c1k6upov1tnp5ox1p foreign key (assistant_id) references assistant (id);
alter table analysis
    add constraint FKc5gdli4c5utte1dsgdbq6465s foreign key (patient_id) references patient (id);
alter table analysis_referral
    add constraint FKhmkk7ijuxnknqmbpfh28t56ur foreign key (assistant_id) references assistant (id);
alter table analysis_referral
    add constraint FK8hrmp7659j3o1quaqxbr0b3ln foreign key (diagnosis_id) references diagnosis (id);
alter table analysis_referral
    add constraint FK4hrjxorf2qawjha0hewq0ypve foreign key (doctor_id) references doctor (id);
alter table analysis_referral
    add constraint FK42s851m62urrlsel1t6cgrkyr foreign key (patient_id) references patient (id);
alter table doctor_appointment
    add constraint FKuurnj3pc0o1p3dwlvl4tm6us foreign key (doctor_id) references doctor (id);
alter table doctor_appointment
    add constraint FK7fb3j2dujjrvpf9xkbbb5qc9c foreign key (patient_id) references patient (id);
alter table doctor_referral
    add constraint FK9rrf1ohid28436u7wehhxble1 foreign key (diagnosis_id) references diagnosis (id);
alter table doctor_referral
    add constraint FKkmpktld8k9s5r7rhx99e5xwl9 foreign key (doctor_initial_id) references doctor (id);
alter table doctor_referral
    add constraint FK38p8qmnmle1y5p8hps8gvl0xb foreign key (doctor_target_id) references doctor (id);
alter table doctor_referral
    add constraint FKrogq4wfgxsfar8g0oicsvlwnr foreign key (patient_id) references patient (id);
alter table examination
    add constraint FKk3s8jt17po9oyasnjg3hshh1i foreign key (functional_diagnostics_doctor_id) references functional_diagnostics_doctor (id);
alter table examination
    add constraint FKmn1l13u1iroqkv46h8dnxcmuw foreign key (patient_id) references patient (id);
alter table examination_referral
    add constraint FK29omeo9ll65275dp31cucyacw foreign key (diagnosis_id) references diagnosis (id);
alter table examination_referral
    add constraint FK5fu23wjhnogs64j9orofp16gi foreign key (doctor_id) references doctor (id);
alter table examination_referral
    add constraint FK5mgdeypgawe9yararqi1mi4ix foreign key (functional_diagnostics_doctor_id) references functional_diagnostics_doctor (id);
alter table examination_referral
    add constraint FKsh54sxhw7qexvkmbhyiwpbsyi foreign key (patient_id) references patient (id);
alter table inspection
    add constraint FKsbo3hyyx522do2ymywwaxccts foreign key (diagnosis_id) references diagnosis (id);
alter table inspection
    add constraint FKll8mvraoyic8m5fqmvtldcqp1 foreign key (doctor_id) references doctor (id);
alter table inspection
    add constraint FKhpphlh1h218arl6qtp2bno2q1 foreign key (patient_id) references patient (id)
