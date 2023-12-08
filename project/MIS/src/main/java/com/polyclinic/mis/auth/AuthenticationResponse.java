package com.polyclinic.mis.auth;

import com.polyclinic.mis.models.PolyclinicUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String email;
    private String token;
}