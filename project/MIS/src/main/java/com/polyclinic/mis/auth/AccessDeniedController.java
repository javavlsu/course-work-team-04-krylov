package com.polyclinic.mis.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccessDeniedController {
    @GetMapping("/AccessDenied")
    public String getAccessDenied(){
        return "/Auth/AccessDenied";
    }
}
