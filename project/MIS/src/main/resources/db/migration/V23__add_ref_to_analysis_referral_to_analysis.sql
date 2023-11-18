alter table analysis add column analysis_referral_id bigint;

alter table analysis add constraint analysis_analysis_referral foreign key (analysis_referral_id) references analysis_referral(id);