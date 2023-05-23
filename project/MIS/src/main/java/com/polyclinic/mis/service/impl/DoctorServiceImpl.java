package com.polyclinic.mis.service.impl;

import com.polyclinic.mis.models.Doctor;
import com.polyclinic.mis.models.Patient;
import com.polyclinic.mis.models.Receptionist;
import com.polyclinic.mis.repository.DoctorRepository;
import com.polyclinic.mis.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PolyclinicUserServiceImpl polyclinicUserService;
    @Override
    public Doctor add(Doctor doctor) {
        return doctorRepository.saveAndFlush(doctor);
    }
    @Override
    public Optional<Doctor> getById(Long id){
        return doctorRepository.findById(id);
    }
    @Override
    public void delete(Long id) {
    doctorRepository.deleteById(id);
    }

    @Override
    public Doctor edit(Doctor doctor) {
        return doctorRepository.saveAndFlush(doctor);
    }

    @Override
    public List<Doctor> getAll() {
        return doctorRepository.findAll();
    }

    @Override
    public Page<Doctor> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection, String fio, String speciality) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        if (fio.equals("")){
            return doctorRepository.findAll("","","",speciality,pageable);
        }
        else{
            String[] splitFio = fio.split(" ");
            switch (splitFio.length){
                case 1:
                    return doctorRepository.findAll(splitFio[0],"","",speciality,pageable);
                case 2:
                    return doctorRepository.findAll(splitFio[0],splitFio[1],"",speciality,pageable);
                case 3:
                    return doctorRepository.findAll(splitFio[0],splitFio[1],splitFio[2],speciality,pageable);
            }
        }
//        else {
//            return receptionistRepository.findAll("","","",birthDate,pageable);
//        }
        return doctorRepository.findAll("","","",speciality,pageable);
    }
    @Override
    public String[] getAllSpecialities(){
        return doctorRepository.findAllSpecialities();
    }

    public List<Doctor> getAllTherapists(){
        return doctorRepository.findAllTherapists();
    }

    public Doctor currentDoctor(){
        return polyclinicUserService.getDoctorFromContext();
    }
}
