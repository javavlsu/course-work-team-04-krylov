package com.polyclinic.mis.auth;

import com.polyclinic.mis.models.PolyclinicUser;
import com.polyclinic.mis.models.Role;
import com.polyclinic.mis.repository.PolyclinicUserRepository;
import com.polyclinic.mis.repository.RoleRepository;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private PolyclinicUserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public UserService(PolyclinicUserRepository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Optional<PolyclinicUser> findUserByEmail(String email) {
        //todo null value condition
        return userRepository.findByEmail(email);
    }


    public PolyclinicUser saveUser(PolyclinicUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        Role userRole = roleRepository.findByName("CanRegisterAsPatient").get();
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(user);

    }
    public PolyclinicUser updateUser(PolyclinicUser user) {
        return userRepository.saveAndFlush(user);

    }
    public List<PolyclinicUser> getAllUsers(){
        return userRepository.findAll();
    }
    public PolyclinicUser getUserById(long id){
        return userRepository.findById(id).get();
    }
}
