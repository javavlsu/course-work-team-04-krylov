alter table analysis_referral drop foreign key FKhmkk7ijuxnknqmbpfh28t56ur;
alter table analysis_referral drop column assistant_id;

alter table examination_referral drop foreign key FK5mgdeypgawe9yararqi1mi4ix;
alter table examination_referral drop column functional_diagnostics_doctor_id;