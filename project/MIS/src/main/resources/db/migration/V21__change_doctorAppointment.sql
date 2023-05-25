alter table doctor_appointment drop constraint doctor_appointment_diagnosis;

alter table doctor_appointment drop column diagnosis_id;

alter table doctor_appointment drop column cabinet_num;

alter table doctor_appointment drop column date;

alter table doctor_appointment add column date date;

alter table doctor_appointment add column time time;