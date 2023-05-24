package com.polyclinic.mis.service.impl;

import com.polyclinic.mis.models.Assistant;
import com.polyclinic.mis.models.DoctorAppointmentTime;
import com.polyclinic.mis.repository.AssistantRepository;
import com.polyclinic.mis.repository.DoctorAppointmentTimeRepository;
import com.polyclinic.mis.service.AssistantService;
import com.polyclinic.mis.service.DoctorAppointmentTimeService;
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
public class DoctorAppointmentTimeServiceImpl implements DoctorAppointmentTimeService {
    @Autowired
    private DoctorAppointmentTimeRepository doctorAppointmentTimeRepository;

    @Override
    public DoctorAppointmentTime add(DoctorAppointmentTime doctorAppointmentTime) {
        return doctorAppointmentTimeRepository.saveAndFlush(doctorAppointmentTime);
    }
    @Override
    public Optional<DoctorAppointmentTime> getById(Long id){
        return doctorAppointmentTimeRepository.findById(id);
    }
    @Override
    public void delete(Long id) {
        doctorAppointmentTimeRepository.deleteById(id);
    }

    @Override
    public DoctorAppointmentTime edit(DoctorAppointmentTime doctorAppointmentTime) {
        return doctorAppointmentTimeRepository.saveAndFlush(doctorAppointmentTime);
    }

    @Override
    public List<DoctorAppointmentTime> getAll() {
        return doctorAppointmentTimeRepository.findAll();
    }

    @Override
    public Page<DoctorAppointmentTime> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection, String fio) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        if (fio.equals("")){
            return doctorAppointmentTimeRepository.findAll(pageable);
        }
        else{
            String[] splitFio = fio.split(" ");
            switch (splitFio.length){
                case 1:
                    return doctorAppointmentTimeRepository.findAll(splitFio[0],"","",pageable);
                case 2:
                    return doctorAppointmentTimeRepository.findAll(splitFio[0],splitFio[1],"",pageable);
                case 3:
                    return doctorAppointmentTimeRepository.findAll(splitFio[0],splitFio[1],splitFio[2],pageable);
            }
        }
        return doctorAppointmentTimeRepository.findAll(pageable);
    }

}
