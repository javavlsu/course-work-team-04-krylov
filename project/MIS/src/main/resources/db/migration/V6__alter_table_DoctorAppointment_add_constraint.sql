alter table doctor_appointment add column doctor_referral_id bigint;
alter table doctor_appointment
    add constraint doctorAppointment_doctorReferral foreign key (doctor_referral_id) references doctor_referral (id);