package com.polyclinic.mis.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleAssigment {
//    @OneToOne
//    private PolyclinicUser polyclinicUser;
//    @OneToOne
//    private Role role;
    private long userId;
    private long roleId;
}
