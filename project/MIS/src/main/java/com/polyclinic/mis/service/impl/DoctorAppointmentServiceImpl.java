package com.polyclinic.mis.service.impl;

import com.polyclinic.mis.models.DoctorAppointment;
import com.polyclinic.mis.models.DoctorReferralAppointment;
import com.polyclinic.mis.repository.DoctorAppointmentRepository;
import com.polyclinic.mis.repository.DoctorReferralAppointmentRepository;
import com.polyclinic.mis.service.DoctorAppointmentService;
import com.polyclinic.mis.service.DoctorReferralAppointmentService;
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
public class DoctorAppointmentServiceImpl implements DoctorAppointmentService {
    @Autowired
    private DoctorAppointmentRepository doctorAppointmentRepository;
    @Autowired
    private PolyclinicUserServiceImpl polyclinicUserService;
    @Override
    public DoctorAppointment add(DoctorAppointment doctorAppointment) {
        return doctorAppointmentRepository.saveAndFlush(doctorAppointment);
    }
    @Override
    public Optional<DoctorAppointment> getById(Long id){
        return doctorAppointmentRepository.findById(id);
    }
    @Override
    public void delete(Long id) {
        doctorAppointmentRepository.deleteById(id);
    }

    @Override
    public DoctorAppointment edit(DoctorAppointment doctorAppointment) {
        return doctorAppointmentRepository.saveAndFlush(doctorAppointment);
    }

    @Override
    public List<DoctorAppointment> getAll() {
        return doctorAppointmentRepository.findAll();
    }
    @Override
    public Page<DoctorAppointment> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection, String fio, String birthDate, String status) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        if (birthDate.equals("")&&fio.equals("")){
            return doctorAppointmentRepository.findAll(status,pageable);
        }
        else if (birthDate.equals("")){
            String[] splitFio = fio.split(" ");
            switch (splitFio.length){
                case 1:
                    return doctorAppointmentRepository.findAll(splitFio[0],"","","",status,pageable);
                case 2:
                    return doctorAppointmentRepository.findAll(splitFio[0],splitFio[1],"","",status,pageable);
                case 3:
                    return doctorAppointmentRepository.findAll(splitFio[0],splitFio[1],splitFio[2],"",status,pageable);
            }
        }
        else if (!fio.equals("")){

            String[] splitFio = fio.split(" ");
            switch (splitFio.length){
                case 1:
                    return doctorAppointmentRepository.findAll(splitFio[0],"","",birthDate,status,pageable);
                case 2:
                    return doctorAppointmentRepository.findAll(splitFio[0],splitFio[1],"",birthDate,status,pageable);
                case 3:
                    return doctorAppointmentRepository.findAll(splitFio[0],splitFio[1],splitFio[2],birthDate,status,pageable);
            }
        }
        else {
            return doctorAppointmentRepository.findAll("","","",birthDate,status,pageable);
        }
        return doctorAppointmentRepository.findAll(status,pageable);
    }
    @Override
    public Page<DoctorAppointment> patientFindPaginated(int pageNumber, int pageSize, String sortField, String sortDirection, String status) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        var patient = polyclinicUserService.getPatientFromContext();
            return doctorAppointmentRepository.findForOnePatient(status,patient.getId(),pageable);

    }

}
