create table doctor_appointment
(
    id                bigint not null auto_increment,
    diagnosis_id      varchar(255),
    doctor_id         bigint,
    patient_id        bigint,
    cabinet_num       varchar(255),
    status            varchar(255),
    date              datetime,
    primary key (id)
) engine=InnoDB;

alter table doctor_appointment
    add constraint doctor_appointment_doctor foreign key (doctor_id) references doctor (id);
alter table doctor_appointment
    add constraint doctor_appointment_patient foreign key (patient_id) references patient (id);
alter table doctor_appointment
    add constraint doctor_appointment_diagnosis foreign key (diagnosis_id) references diagnosis (id);