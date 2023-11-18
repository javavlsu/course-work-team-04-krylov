alter table assistant add column user_id bigint;
alter table assistant add constraint assistant_user foreign key (user_id) references user (id);

alter table doctor add column user_id bigint;
alter table doctor add constraint doctor_user foreign key (user_id) references user (id);

alter table functional_diagnostics_doctor add column user_id bigint;
alter table functional_diagnostics_doctor add constraint functional_diagnostics_doctor_user foreign key (user_id) references user (id);

alter table patient add column user_id bigint;
alter table patient add constraint patient_user foreign key (user_id) references user (id);

alter table receptionist add column user_id bigint;
alter table receptionist add constraint receptionist_user foreign key (user_id) references user (id);