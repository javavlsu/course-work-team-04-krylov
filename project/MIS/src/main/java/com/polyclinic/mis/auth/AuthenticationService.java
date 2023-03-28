package com.polyclinic.mis.auth;

import com.polyclinic.mis.config.JwtService;
import com.polyclinic.mis.models.PolyclinicUser;
import com.polyclinic.mis.models.Role;
import com.polyclinic.mis.repository.PolyclinicUserRepository;
import com.polyclinic.mis.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final PolyclinicUserRepository polyclinicUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;

    public void register(RegisterRequest request) {
        var user = PolyclinicUser.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(List.of(roleRepository.findByName("CanRegisterAsPatient").get()))
                .build();
        polyclinicUserRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
    }

    public void authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate((
                new UsernamePasswordAuthenticationToken(request.getEmail(),
                        request.getPassword())
                )
        );
        var user = polyclinicUserRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
    }
}
