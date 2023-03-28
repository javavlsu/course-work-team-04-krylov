package com.polyclinic.mis.repository;

import com.polyclinic.mis.models.PolyclinicUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PolyclinicUserRepository extends JpaRepository<PolyclinicUser,Long> {
    Optional<PolyclinicUser> findByEmail(String email);
    Boolean existsByEmail(String email);
}
