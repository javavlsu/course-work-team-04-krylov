package com.polyclinic.mis.auth;

import com.polyclinic.mis.models.Analysis;
import com.polyclinic.mis.models.PolyclinicUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping("/Auth")
@RequiredArgsConstructor
public class AuthenticationController {
//    private final AuthenticationService authenticationService;

    @Autowired
    private UserService userService;
//    @Autowired
//    private PolyclinicUserDetailsService polyclinicUserDetailsService;
    @Autowired
    private final AuthenticationManager authenticationManager;

    @GetMapping("/Register")
    public String ShowRegister(Model model){
//        PolyclinicUser polyclinicUser = new PolyclinicUser();
//        model.addAttribute("polyclinicUser",polyclinicUser);

        return "/Auth/Register";
    }
//    @PostMapping("/Register")
//    public String Register(RegisterRequest request){
//        authenticationService.register(request);
//        return "redirect:/";
//    }


//@PostMapping("/Register")
//    public String Register(@ModelAttribute("user")PolyclinicUser user){
//    userService.saveUser(user);
//    authenticationManager.authenticate((
//                    new UsernamePasswordAuthenticationToken(user.getEmail(),
//                            user.getPassword())
//            )
//    );
//    return "redirect:/";
//}
    @GetMapping("/Authenticate")
    public String ShowAuthenticate(Model model){
//        PolyclinicUser polyclinicUser = new PolyclinicUser();
//        model.addAttribute("polyclinicUser",polyclinicUser);
        return "/Auth/Authenticate";
    }
////    @PostMapping("/Authenticate")
////    public String Authenticate(AuthenticationRequest request){
////        authenticationService.authenticate(request);
////        return "redirect:/";
////    }
//    @PostMapping("/Authenticate")
//    public String Authenticate(@ModelAttribute("user")PolyclinicUser user){
//        authenticationManager.authenticate((
//                        new UsernamePasswordAuthenticationToken(user.getEmail(),
//                                user.getPassword())
//                )
//        );
//        return "redirect:/";
//    }

//    @GetMapping("/AccessDenied")
//    public String accessDenied(Model model){
//        return "/Auth/AccessDenied";
//    }
}


