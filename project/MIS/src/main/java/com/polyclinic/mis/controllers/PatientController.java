package com.polyclinic.mis.controllers;

import com.polyclinic.mis.auth.UserService;
import com.polyclinic.mis.models.AnalysisReferral;
import com.polyclinic.mis.models.Examination;
import com.polyclinic.mis.models.Patient;
import com.polyclinic.mis.models.PolyclinicUser;
import com.polyclinic.mis.repository.RoleRepository;
import com.polyclinic.mis.service.impl.FunctionalDiagnosticsDoctorServiceImpl;
import com.polyclinic.mis.service.impl.PatientServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class PatientController {

    @Autowired
    PatientServiceImpl patientService;
//    @Autowired
//    RoleRepository roleRepository;
    @Autowired
    UserService userService;
    @Autowired
    AuthenticationManager authenticationManager;
    @GetMapping("/Patients/Index")
    public String Index(Model model){
//        Iterable<Patient> patients = patientService.getAll();
//        model.addAttribute("patients",patients);
        return findPaginated(1, "lastName" , "desc","","",model);
        //return "/Patients/Index";
    }

    @GetMapping("/Patients/Index/{pageNumber}")
    public String findPaginated(
            @PathVariable (value = "pageNumber") int pageNumber,
            @RequestParam(value = "sortField") String sortField,
            @RequestParam(value = "sortDir") String sortDir,
            @RequestParam(value = "patientFIO") String patientFio,
            @RequestParam(value = "patientBirthDate") String patientBirthDate,
            Model model){
        //todo page size from page https://www.youtube.com/watch?v=Aie8n12EFQc 11 00
        int pageSize = 5;
        Page<Patient> page = patientService.findPaginated(pageNumber,pageSize,sortField,sortDir,patientFio,patientBirthDate);
        List<Patient> patients = page.getContent();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("patients", patients);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc":"asc");
        model.addAttribute("patientFIO",patientFio);
        model.addAttribute("patientBirthDate",patientBirthDate);
        return "/Patients/Index";
    }



    @GetMapping("/Patients/Create")
    public String ShowCreate(Model model){
        Patient patient = new Patient();
        model.addAttribute("patient",patient);
        return "/Patients/Create";
    }
    @PostMapping("/Patients/Create")
    public String Create(@Valid @ModelAttribute("patient")Patient patient,
                         BindingResult result,
                         Model model){
        if (result.hasErrors()){
            return "/Patients/Create";
        }
        else {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = authentication.getName();
            var user = userService.findUserByEmail(email);
            patient.setUser(user.get());
            patientService.add(patient);
            patientService.assignRole(patient, user.get());
            return "redirect:/";
        }
    }
//    private void assignRole(Patient patient, PolyclinicUser user){
//        var patientUser= patient.getUser();
//        var roles = user.getRoles();
//        var canRegisterAsPatientRole = roleRepository.findByName("CanRegisterAsPatient").get();
//        roles.remove(canRegisterAsPatientRole);
//        var patientRole = roleRepository.findByName("Patient").get();
//        roles.add(patientRole);
//        patientUser.setRoles(roles);
//        userService.updateUser(patientUser);
//
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        List<GrantedAuthority> updatedAuthorities = new ArrayList<>(auth.getAuthorities());
//        updatedAuthorities.remove(new SimpleGrantedAuthority(canRegisterAsPatientRole.getName()));
//        updatedAuthorities.add(new SimpleGrantedAuthority(patientRole.getName()));
//
//        Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), updatedAuthorities);
//
//        SecurityContextHolder.getContext().setAuthentication(newAuth);
//    }


    @GetMapping("Patients/Edit/{id}")
    public String ShowEdit(@PathVariable(value = "id") long id, Model model){
        Optional<Patient> patient = patientService.getById(id);
        if (patient.isPresent()){
            model.addAttribute("patient",patient.get());
            return "/Patients/Update";
        }
        else {
            //todo
            return "/Error";
        }
    }
    @GetMapping("Patients/Delete/{id}")
    public String Delete(@PathVariable (value = "id") long id, Model model){
        patientService.delete(id);
        return "redirect:/Patients/Index";
    }
    @GetMapping("Patients/Details/{id}")
    public String Details(@PathVariable (value = "id") long id, Model model){
        Optional<Patient> patient = patientService.getById(id);
        if (patient.isPresent()){
            model.addAttribute("patient",patient.get());

            model.addAttribute("ownProfile", false);
            return "/Patients/Details";
        }
        else {
            //todo
            return "/Error";
        }
    }

    @GetMapping("PatientProfile")
    public String Profile(Model model){

        model.addAttribute("patient",patientService.currentPatient());
        model.addAttribute("ownProfile", true);
        return "/Patients/Details";
    }

//    @ResponseBody
//    @GetMapping("Patients/getForSearch/{q}")
//    public List<Patient> getPatientsForSearch(@PathVariable(value = "q",required = false) String query{
//        return patientService.getAll();
//    }
}
