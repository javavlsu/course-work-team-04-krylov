package com.polyclinic.mis.service.impl;

import com.polyclinic.mis.models.DoctorAppointment;
import com.polyclinic.mis.models.DoctorReferralAppointment;
import com.polyclinic.mis.repository.DoctorReferralAppointmentRepository;
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
public class DoctorReferralAppointmentServiceImpl implements DoctorReferralAppointmentService {
    @Autowired
    private DoctorReferralAppointmentRepository doctorReferralAppointmentRepository;
    @Autowired
    private PolyclinicUserServiceImpl polyclinicUserService;
    @Override
    public DoctorReferralAppointment add(DoctorReferralAppointment doctorReferralAppointment) {
        return doctorReferralAppointmentRepository.saveAndFlush(doctorReferralAppointment);
    }
    @Override
    public Optional<DoctorReferralAppointment> getById(Long id){
        return doctorReferralAppointmentRepository.findById(id);
    }
    @Override
    public void delete(Long id) {
    doctorReferralAppointmentRepository.deleteById(id);
    }

    @Override
    public DoctorReferralAppointment edit(DoctorReferralAppointment doctorReferralAppointment) {
        return doctorReferralAppointmentRepository.saveAndFlush(doctorReferralAppointment);
    }

    @Override
    public List<DoctorReferralAppointment> getAll() {
        return doctorReferralAppointmentRepository.findAll();
    }
    @Override
    public Page<DoctorReferralAppointment> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection, String fio, String birthDate, String status) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        if (birthDate.equals("")&&fio.equals("")){
            return doctorReferralAppointmentRepository.findAll(status,pageable);
        }
        else if (birthDate.equals("")){
            String[] splitFio = fio.split(" ");
            switch (splitFio.length){
                case 1:
                    return doctorReferralAppointmentRepository.findAll(splitFio[0],"","","",status,pageable);
                case 2:
                    return doctorReferralAppointmentRepository.findAll(splitFio[0],splitFio[1],"","",status,pageable);
                case 3:
                    return doctorReferralAppointmentRepository.findAll(splitFio[0],splitFio[1],splitFio[2],"",status,pageable);
            }
        }
        else if (!fio.equals("")){

            String[] splitFio = fio.split(" ");
            switch (splitFio.length){
                case 1:
                    return doctorReferralAppointmentRepository.findAll(splitFio[0],"","",birthDate,status,pageable);
                case 2:
                    return doctorReferralAppointmentRepository.findAll(splitFio[0],splitFio[1],"",birthDate,status,pageable);
                case 3:
                    return doctorReferralAppointmentRepository.findAll(splitFio[0],splitFio[1],splitFio[2],birthDate,status,pageable);
            }
        }
        else {
            return doctorReferralAppointmentRepository.findAll("","","",birthDate,status,pageable);
        }
        return doctorReferralAppointmentRepository.findAll(status,pageable);
    }
    @Override
    public Page<DoctorReferralAppointment> patientFindPaginated(int pageNumber, int pageSize, String sortField, String sortDirection, String status) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        var patient = polyclinicUserService.getPatientFromContext();
        return doctorReferralAppointmentRepository.findForOnePatient(status,patient.getId(),pageable);

    }

}
