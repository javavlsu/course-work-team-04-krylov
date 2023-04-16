package com.polyclinic.mis;

import com.polyclinic.mis.auth.PolyclinicUserDetailsService;
import com.polyclinic.mis.auth.UserService;
import com.polyclinic.mis.models.PolyclinicUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AuthTest {
    @Autowired
    private UserService userService;
//    @Test
//    void readUserRoles(){
//
//        var user = userService.findUserByEmail("krylov.em2002@gmail.com");
//        System.out.println(user.getEmail()+" "+user.getPassword()+" "+user.getRoles());
//    }
}
