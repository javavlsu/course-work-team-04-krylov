alter table assistant drop constraint assistant_cabinet;
alter table assistant drop column cabinet;
alter table functional_diagnostics_doctor drop constraint functional_diagnostics_doctor_cabinet;
alter table functional_diagnostics_doctor drop column cabinet;
alter table doctor drop constraint doctor_cabinet;
alter table doctor drop column cabinet;