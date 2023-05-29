package com.polyclinic.mis.config;

import com.polyclinic.mis.auth.CustomAccessDeniedHandler;
import com.polyclinic.mis.auth.PolyclinicUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfiguration{
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private PolyclinicUserDetailsService userDetailsService;


//    @Bean
//    public AuthenticationManager userDetailsService(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .userDetailsService(userDetailsService)
//                .passwordEncoder(bCryptPasswordEncoder);
//        return auth.build();
//    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new CustomAccessDeniedHandler();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests()
                //Главная
                .requestMatchers("/").permitAll()

                .requestMatchers("/Error").permitAll()
                //Регистрация
                .requestMatchers("/Authenticate").permitAll()
                .requestMatchers("/Register").permitAll()
                //Подтверждение данных
                //Админ
                .requestMatchers("/AssignUserToARole/Create").hasAuthority("Admin")
                .requestMatchers("/error").hasAnyAuthority("Admin")
                //Анализ
//                .requestMatchers("/Analyses").hasAnyAuthority("Doctor","Assistant","Admin")
                .requestMatchers("/Analyses/Details/**").hasAnyAuthority("Doctor","Assistant","Admin")
                .requestMatchers("/Analyses/Index/**").hasAnyAuthority("Doctor","Assistant","Admin")

                .requestMatchers("/AssistantAnalyses/Index/**").hasAnyAuthority("Doctor","Assistant","Admin")
                .requestMatchers("/AssistantAnalyses/Details/**").hasAnyAuthority("Doctor","Assistant","Admin")
                .requestMatchers("/AssistantAnalyses/Delete/**").hasAnyAuthority("Doctor","Assistant","Admin")
                .requestMatchers("/AssistantAnalyses/Edit/**").hasAnyAuthority("Doctor","Assistant","Admin")
                .requestMatchers("/AssistantAnalyses/Create").hasAnyAuthority("Assistant","Admin")
                .requestMatchers("/AssistantAnalyses/Create/**").hasAnyAuthority("Assistant","Admin")


                .requestMatchers("/PatientAnalyses/Details/**").hasAnyAuthority("Patient","Admin")
                .requestMatchers("/PatientAnalyses/Index/**").hasAnyAuthority("Patient","Admin")
                .requestMatchers("/Analyses/Create").hasAnyAuthority("Assistant","Admin")
                .requestMatchers("/Analyses/Create/**").hasAnyAuthority("Assistant","Admin")
                .requestMatchers("/Analyses/Edit/**").hasAnyAuthority("Assistant","Admin")
                .requestMatchers("/Analyses/Delete/**").hasAnyAuthority("Admin")


                //Направление на анализ
                .requestMatchers("/AnalysisReferrals/Details/**").hasAnyAuthority("Doctor","Assistant","Admin","Receptionist")
                .requestMatchers("/AnalysisReferrals/Index/**").hasAnyAuthority("Doctor","Assistant","Admin","Receptionist")
                .requestMatchers("/AssistantAnalysisReferrals/Index/**").hasAnyAuthority("Doctor","Assistant","Admin","Receptionist")

                .requestMatchers("/PatientAnalysisReferrals/Details/**").hasAnyAuthority("Patient","Admin")
                .requestMatchers("/PatientAnalysisReferrals/Index/**").hasAnyAuthority("Patient","Admin")
                .requestMatchers("/AnalysisReferrals/Create").hasAnyAuthority("Assistant","Admin","Receptionist","Doctor")
                .requestMatchers("/AnalysisReferrals/Create/**").hasAnyAuthority("Assistant","Admin","Receptionist","Doctor")
                .requestMatchers("/AnalysisReferrals/Edit/**").hasAnyAuthority("Assistant","Admin","Receptionist","Doctor")
                .requestMatchers("/AnalysisReferrals/Delete/**").hasAnyAuthority("Admin")
                .requestMatchers("/AssistantAnalysisReferrals/CreateAnalysis/**").hasAnyAuthority("Admin","Assistant")

                //Пациенты
                .requestMatchers("/Patients/Details/**").hasAnyAuthority("Doctor","Assistant","Admin","Receptionist","FunctionalDiagnosticsDoctor")
                .requestMatchers("/Patients/Index/**").hasAnyAuthority("Doctor","Assistant","Admin","Receptionist","FunctionalDiagnosticsDoctor")
                .requestMatchers("/Patients/Create").hasAnyAuthority("Admin","CanRegisterAsPatient")
                .requestMatchers("/Patients/Edit/**").hasAnyAuthority("Assistant","Admin","Receptionist","FunctionalDiagnosticsDoctor")
                .requestMatchers("/Patients/Delete/**").hasAnyAuthority("Admin")

                //Врачи функциональной диагностики
                .requestMatchers("/FunctionalDiagnosticsDoctors/Details/**").hasAnyAuthority("Admin","Receptionist","FunctionalDiagnosticsDoctor")
                .requestMatchers("/FunctionalDiagnosticsDoctors/Index/**").hasAnyAuthority("Doctor","Assistant","Admin","Receptionist","FunctionalDiagnosticsDoctor")
                .requestMatchers("/FunctionalDiagnosticsDoctors/Create").hasAnyAuthority("Admin","CanRegisterAsFunctionalDiagnosticsDoctor")
                .requestMatchers("/FunctionalDiagnosticsDoctors/Edit/**").hasAnyAuthority("Assistant","Admin","Receptionist","FunctionalDiagnosticsDoctor")
                .requestMatchers("/FunctionalDiagnosticsDoctors/Delete/**").hasAnyAuthority("Admin")

                //Обследования
                .requestMatchers("/Examinations/Details/**").hasAnyAuthority("Doctor","Admin","FunctionalDiagnosticsDoctor")
                .requestMatchers("/Examinations/Index/**").hasAnyAuthority("Doctor","Admin","FunctionalDiagnosticsDoctor")
                .requestMatchers("/PatientExaminations/Details/**").hasAnyAuthority("Patient","Admin")
                .requestMatchers("/PatientExaminations/Index/**").hasAnyAuthority("Patient","Admin")
                .requestMatchers("/Examinations/Create").hasAnyAuthority("Doctor","Admin","FunctionalDiagnosticsDoctor")
                .requestMatchers("/Examinations/Edit/**").hasAnyAuthority("Doctor","Admin","FunctionalDiagnosticsDoctor")
                .requestMatchers("/Examinations/Delete/**").hasAnyAuthority("Admin")



                .requestMatchers("/FunctionalDoctorExaminations/Index/**").hasAnyAuthority("Doctor","FunctionalDiagnosticsDoctor","Admin")
                .requestMatchers("/FunctionalDoctorExaminations/Details/**").hasAnyAuthority("Doctor","FunctionalDiagnosticsDoctor","Admin")
                .requestMatchers("/FunctionalDoctorExaminations/Delete/**").hasAnyAuthority("Doctor","FunctionalDiagnosticsDoctor","Admin")
                .requestMatchers("/FunctionalDoctorExaminations/Edit/**").hasAnyAuthority("Doctor","FunctionalDiagnosticsDoctor","Admin")
                .requestMatchers("/FunctionalDoctorExaminations/Create").hasAnyAuthority("FunctionalDiagnosticsDoctor","Admin")
                .requestMatchers("/FunctionalDoctorExaminations/Create/**").hasAnyAuthority("FunctionalDiagnosticsDoctor","Admin")

                //Направления на обследования
                .requestMatchers("/ExaminationReferrals/Details/**").hasAnyAuthority("Doctor","Admin","FunctionalDiagnosticsDoctor","Receptionist")
                .requestMatchers("/ExaminationReferrals/Index/**").hasAnyAuthority("Doctor","Admin","FunctionalDiagnosticsDoctor","Receptionist")
                .requestMatchers("/PatientExaminationReferrals/Details/**").hasAnyAuthority("Doctor","Admin")
                .requestMatchers("/PatientExaminationReferrals/Index/**").hasAnyAuthority("Patient","Admin")
                .requestMatchers("/ExaminationReferrals/Create").hasAnyAuthority("Doctor","Admin","FunctionalDiagnosticsDoctor","Receptionist")
                .requestMatchers("/ExaminationReferrals/Create/**").hasAnyAuthority("Doctor","Admin","FunctionalDiagnosticsDoctor","Receptionist")

                .requestMatchers("/ExaminationReferrals/Edit/**").hasAnyAuthority("Doctor","Admin","FunctionalDiagnosticsDoctor","Receptionist")
                .requestMatchers("/ExaminationReferrals/Delete/**").hasAnyAuthority("Admin")


                .requestMatchers("/FunctionalDoctorExaminationReferrals/Details/**").hasAnyAuthority("Doctor","Admin","FunctionalDiagnosticsDoctor","Receptionist")
                .requestMatchers("/FunctionalDoctorExaminationReferrals/Index").hasAnyAuthority("Doctor","Admin","FunctionalDiagnosticsDoctor","Receptionist")
                .requestMatchers("/FunctionalDoctorExaminationReferrals/Index/**").hasAnyAuthority("Doctor","Admin","FunctionalDiagnosticsDoctor","Receptionist")
                .requestMatchers("/FunctionalDoctorExaminationReferrals/Create").hasAnyAuthority("Doctor","Admin","FunctionalDiagnosticsDoctor","Receptionist")
                .requestMatchers("/FunctionalDoctorExaminationReferrals/Create/**").hasAnyAuthority("Doctor","Admin","FunctionalDiagnosticsDoctor","Receptionist")

                .requestMatchers("/FunctionalDoctorExaminationReferrals/CreateExamination/**").hasAnyAuthority("Admin","FunctionalDiagnosticsDoctor")



                //Часы приема кабинета функциональной диагностики
                .requestMatchers("/GetExaminationReferralTimes/**").hasAnyAuthority("Admin","Doctor")
                .requestMatchers("/GetExaminationReferralTimes").hasAnyAuthority("Admin","Doctor")

                //Диагнозы
                .requestMatchers("/Diagnoses/Details/**").hasAnyAuthority("Doctor","Admin")
                .requestMatchers("/Diagnoses/Index/**").hasAnyAuthority("Doctor","Admin")
                .requestMatchers("/Diagnoses/Create").hasAnyAuthority("Admin")
                .requestMatchers("/Diagnoses/Edit/**").hasAnyAuthority("Admin")
                .requestMatchers("/Diagnoses/Delete/**").hasAnyAuthority("Admin")


                //Заявления на прием врача по направлению
                .requestMatchers("/DoctorReferralAppointments/Details/**").hasAnyAuthority("Doctor","Admin","Receptionist")
                .requestMatchers("/DoctorReferralAppointments/Index/**").hasAnyAuthority("Doctor","Admin","Receptionist")
                .requestMatchers("/PatientDoctorReferralAppointments/Details/**").hasAnyAuthority("Patient","Admin")
                .requestMatchers("/PatientDoctorReferralAppointments/Index/**").hasAnyAuthority("Patient","Admin")
                .requestMatchers("/DoctorReferralAppointments/Create").hasAnyAuthority("Doctor","Admin","Receptionist")
                .requestMatchers("/DoctorReferralAppointments/Edit/**").hasAnyAuthority("Doctor","Admin","Receptionist")
                .requestMatchers("/DoctorReferralAppointments/Delete/**").hasAnyAuthority("Admin")


                //Осмотры
                .requestMatchers("/Inspections/Details/**").hasAnyAuthority("Doctor","Admin")
                .requestMatchers("/Inspections/Index/**").hasAnyAuthority("Doctor","Admin")
                .requestMatchers("/PatientInspections/Details/**").hasAnyAuthority("Admin","Patient")
                .requestMatchers("/PatientInspections/Index/**").hasAnyAuthority("Admin","Patient")
                .requestMatchers("/Inspections/Create/**").hasAnyAuthority("Doctor","Admin")
                .requestMatchers("/Inspections/Create").hasAnyAuthority("Doctor","Admin")
                .requestMatchers("/Inspections/Edit/**").hasAnyAuthority("Doctor","Admin")
                .requestMatchers("/Inspections/Delete/**").hasAnyAuthority("Admin")



                //Направления ко врачу
                .requestMatchers("/DoctorReferrals/Details/**").hasAnyAuthority("Doctor","Admin","Patient")
                .requestMatchers("/DoctorReferrals/Index/**").hasAnyAuthority("Doctor","Admin","Patient")
                .requestMatchers("/PatientDoctorReferrals/Details/**").hasAnyAuthority("Patient","Admin")
                .requestMatchers("/PatientDoctorReferrals/Index/**").hasAnyAuthority("Patient","Admin")
                .requestMatchers("/DoctorReferrals/Create").hasAnyAuthority("Doctor","Admin")
                .requestMatchers("/DoctorReferrals/Edit/**").hasAnyAuthority("Doctor","Admin")
                .requestMatchers("/DoctorReferrals/Delete/**").hasAnyAuthority("Admin")


                //Лаборанты
                .requestMatchers("/Assistants/Details/**").hasAnyAuthority("Admin")
                .requestMatchers("/Assistants/Index/**").hasAnyAuthority("Admin")
                .requestMatchers("/Assistants/Create").hasAnyAuthority("Admin","CanRegisterAsAssistant")
                .requestMatchers("/Assistants/Edit/**").hasAnyAuthority("Admin")
                .requestMatchers("/Assistants/Delete/**").hasAnyAuthority("Admin")


                //Мед регистраторы
                .requestMatchers("/Receptionists/Details/**").hasAnyAuthority("Admin")
                .requestMatchers("/Receptionists/Index/**").hasAnyAuthority("Admin")
                .requestMatchers("/Receptionists/Create").hasAnyAuthority("Admin","CanRegisterAsReceptionist")
                .requestMatchers("/Receptionists/Edit/**").hasAnyAuthority("Admin")
                .requestMatchers("/Receptionists/Delete/**").hasAnyAuthority("Admin")


                //Врачи
                .requestMatchers("/Doctors/Details/**").hasAnyAuthority("Admin","Patient")
                .requestMatchers("/Doctors/Index/**").hasAnyAuthority("Admin","Patient")
                .requestMatchers("/Doctors/Create").hasAnyAuthority("Admin","CanRegisterAsDoctor")
                .requestMatchers("/Doctors/Edit/**").hasAnyAuthority("Admin")
                .requestMatchers("/Doctors/Delete/**").hasAnyAuthority("Admin")


                //Заявления на первичный прием у врача
                .requestMatchers("/DoctorAppointments/Details/**").hasAnyAuthority("Doctor","Admin","Receptionist")
                .requestMatchers("/DoctorAppointments/Index/**").hasAnyAuthority("Doctor","Admin","Receptionist")
                .requestMatchers("/PatientDoctorAppointments/Index/**").hasAnyAuthority("Admin","Patient")
                .requestMatchers("/PatientDoctorAppointments/Create").hasAnyAuthority("Admin","Patient")
                .requestMatchers("/CreateInspection/**").hasAnyAuthority("Doctor","Admin","Receptionist")
                .requestMatchers("/CreateInspection").hasAnyAuthority("Doctor","Admin","Receptionist")



                .requestMatchers("/DoctorAppointments/Edit/**").hasAnyAuthority("Doctor","Admin","Receptionist")
                .requestMatchers("/DoctorAppointments/Delete/**").hasAnyAuthority("Admin")

                //Часы приема работы терапевтов
                .requestMatchers("/GetAppointmentTimes/**").hasAnyAuthority("Admin","Patient")
                .requestMatchers("/GetAppointmentTimes").hasAnyAuthority("Admin","Patient")

                //Профиль пациента
                .requestMatchers("/PatientProfile").hasAnyAuthority("Admin","Patient")

                //Кабинеты для анализов
                .requestMatchers("/AnalysisCabinets/Details/**").hasAnyAuthority("Admin","Doctor","Assistant","Patient")
                .requestMatchers("/AnalysisCabinets/Index/**").hasAnyAuthority("Admin","Doctor","Assistant","Patient")
                .requestMatchers("/AnalysisCabinets/Create").hasAnyAuthority("Admin")
                .requestMatchers("/AnalysisCabinets/Edit/**").hasAnyAuthority("Admin")
                .requestMatchers("/AnalysisCabinets/Delete/**").hasAnyAuthority("Admin")

                //Кабинеты для обследований
                .requestMatchers("/ExaminationCabinets/Details/**").hasAnyAuthority("Admin","Doctor","FunctionalDiagnosticsDoctor","Patient")
                .requestMatchers("/ExaminationCabinets/Index/**").hasAnyAuthority("Admin","Doctor","FunctionalDiagnosticsDoctor","Patient")
                .requestMatchers("/ExaminationCabinets/Create").hasAnyAuthority("Admin")
                .requestMatchers("/ExaminationCabinets/Edit/**").hasAnyAuthority("Admin")
                .requestMatchers("/ExaminationCabinets/Delete/**").hasAnyAuthority("Admin")

                //Кабинеты врачей
                .requestMatchers("/DoctorCabinets/Details/**").hasAnyAuthority("Admin","Doctor","Patient")
                .requestMatchers("/DoctorCabinets/Index/**").hasAnyAuthority("Admin","Doctor","Patient")
                .requestMatchers("/DoctorCabinets/Create").hasAnyAuthority("Admin")
                .requestMatchers("/DoctorCabinets/Edit/**").hasAnyAuthority("Admin")
                .requestMatchers("/DoctorCabinets/Delete/**").hasAnyAuthority("Admin")


                //Время работы узких специалистов
                .requestMatchers("/DoctorAppointmentTimes/Details/**").hasAnyAuthority("Admin")
                .requestMatchers("/DoctorAppointmentTimes/Index/**").hasAnyAuthority("Admin")
                .requestMatchers("/DoctorAppointmentTimes/Create").hasAnyAuthority("Admin")
                .requestMatchers("/DoctorAppointmentTimes/Create/**").hasAnyAuthority("Admin")

                .requestMatchers("/DoctorAppointmentTimes/Edit/**").hasAnyAuthority("Admin")
                .requestMatchers("/DoctorAppointmentTimes/Delete/**").hasAnyAuthority("Admin")
                //Время работы терапевтов
                .requestMatchers("/TherapistAppointmentTimes/Details/**").hasAnyAuthority("Admin")
                .requestMatchers("/TherapistAppointmentTimes/Index/**").hasAnyAuthority("Admin")
                .requestMatchers("/TherapistAppointmentTimes/Create").hasAnyAuthority("Admin")
                .requestMatchers("/TherapistAppointmentTimes/Create/**").hasAnyAuthority("Admin")

                .requestMatchers("/TherapistAppointmentTimes/Edit/**").hasAnyAuthority("Admin")
                .requestMatchers("/TherapistAppointmentTimes/Delete/**").hasAnyAuthority("Admin")

                //Время работы кабинетов для обследований
                .requestMatchers("/ExaminationCabinetReferralTimes/Details/**").hasAnyAuthority("Admin")
                .requestMatchers("/ExaminationCabinetReferralTimes/Index/**").hasAnyAuthority("Admin")
                .requestMatchers("/ExaminationCabinetReferralTimes/Create").hasAnyAuthority("Admin")
                .requestMatchers("/ExaminationCabinetReferralTimes/Create/**").hasAnyAuthority("Admin")

                .requestMatchers("/ExaminationCabinetReferralTimes/Edit/**").hasAnyAuthority("Admin")
                .requestMatchers("/ExaminationCabinetReferralTimes/Delete/**").hasAnyAuthority("Admin")


//                .authenticated()
//                .requestMatchers("/Analyses").hasAuthority("Admin")
//                .requestMatchers("/Examinations/**").authenticated()
//                .requestMatchers("/admin/**").hasAuthority("Admin").anyRequest().authenticated()
                .and()
//                .csrf().disable()
                .formLogin()
                .loginPage("/Authenticate")
                .loginProcessingUrl("/Authenticate")
                .failureUrl("/Authenticate?error=true")
//                .successForwardUrl("/")
//                .defaultSuccessUrl("/")
                .usernameParameter("email")
                .passwordParameter("password")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).deleteCookies("JSESSIONID")
//                .logoutSuccessUrl("/")
//                .and().exceptionHandling()
//                .accessDeniedHandler(accessDeniedHandler())

                .and()
                .userDetailsService(userDetailsService)
                ;
//        http.httpBasic();
        return http.build();
    }


}

