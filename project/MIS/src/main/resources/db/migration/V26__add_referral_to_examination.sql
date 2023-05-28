alter table examination add column examination_referral_id bigint;

alter table examination add constraint examination_examination_referral foreign key (examination_referral_id) references examination_referral(id);