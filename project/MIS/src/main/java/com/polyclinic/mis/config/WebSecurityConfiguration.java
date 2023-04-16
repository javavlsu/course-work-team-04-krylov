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
                //Регистрация
                .requestMatchers("/Authenticate").permitAll()
                .requestMatchers("/Register").permitAll()
                //Подтверждение данных
                .requestMatchers("/Patients/Create").hasAuthority("CanRegisterAsPatient")
                .requestMatchers("/Receptionists/Create").hasAuthority("CanRegisterAsReceptionist")
                .requestMatchers("/FunctionalDiagnosticsDoctors/Create").hasAuthority("CanRegisterAsFunctionalDiagnosticsDoctor")
                .requestMatchers("/Doctors/Create").hasAuthority("CanRegisterAsDoctor")
                .requestMatchers("/Assistants/Create").hasAuthority("CanRegisterAsAssistant")
                //Админ
                .requestMatchers("/AssignUserToARole/Create").hasAuthority("Admin")
                //Анализ
//                .requestMatchers("/Analyses").hasAnyAuthority("Doctor","Assistant","Admin")
                .requestMatchers("/Analyses/Details").hasAnyAuthority("Doctor","Assistant","Admin")
                .requestMatchers("/Analyses/Index/**").hasAnyAuthority("Doctor","Assistant","Admin")
                .requestMatchers("/Analyses/Create").hasAnyAuthority("Assistant","Admin")
                .requestMatchers("/Analyses/Update").hasAnyAuthority("Assistant","Admin")

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

