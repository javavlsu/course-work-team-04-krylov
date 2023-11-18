create table doctor_appointment_time
(
    id  bigint not null auto_increment,
    doctor_id bigint,
    time time,
    primary key (id)
) engine=InnoDB;

alter table doctor_appointment_time add constraint doctor_appointment_time_doctor foreign key (doctor_id) references doctor (id);

create table therapist_appointment_time
(
    id  bigint not null auto_increment,
    doctor_id bigint,
    day_of_the_week varchar(255),
    time time,
    primary key (id)
) engine=InnoDB;

alter table therapist_appointment_time add constraint therapist_appointment_time_doctor foreign key (doctor_id) references doctor (id);