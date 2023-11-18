package com.polyclinic.mis.service.impl;

import com.polyclinic.mis.models.DoctorAppointmentTime;
import com.polyclinic.mis.models.TherapistAppointmentTime;
import com.polyclinic.mis.repository.DoctorAppointmentTimeRepository;
import com.polyclinic.mis.repository.TherapistAppointmentTimeRepository;
import com.polyclinic.mis.service.DoctorAppointmentTimeService;
import com.polyclinic.mis.service.TherapistAppointmentTimeService;
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
public class TherapistAppointmentTimeServiceImpl implements TherapistAppointmentTimeService {
    @Autowired
    private TherapistAppointmentTimeRepository therapistAppointmentTimeRepository;

    @Override
    public TherapistAppointmentTime add(TherapistAppointmentTime therapistAppointmentTime) {
        return therapistAppointmentTimeRepository.saveAndFlush(therapistAppointmentTime);
    }
    @Override
    public Optional<TherapistAppointmentTime> getById(Long id){
        return therapistAppointmentTimeRepository.findById(id);
    }
    @Override
    public void delete(Long id) {
        therapistAppointmentTimeRepository.deleteById(id);
    }

    @Override
    public TherapistAppointmentTime edit(TherapistAppointmentTime therapistAppointmentTime) {
        return therapistAppointmentTimeRepository.saveAndFlush(therapistAppointmentTime);
    }

    @Override
    public List<TherapistAppointmentTime> getAll() {
        return therapistAppointmentTimeRepository.findAll();
    }

    public List<TherapistAppointmentTime> getByDoctorId(long id) {
        return therapistAppointmentTimeRepository.findByDoctorId(id);
    }

    public List<TherapistAppointmentTime> getByDoctorIdAndWeekDay(long id,String weekDay) {
        return therapistAppointmentTimeRepository.findByDoctorIdAndWeekDay(id, weekDay);
    }

    @Override
    public Page<TherapistAppointmentTime> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection, String fio) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        if (fio.equals("")){
            return therapistAppointmentTimeRepository.findAll(pageable);
        }
        else{
            String[] splitFio = fio.split(" ");
            switch (splitFio.length){
                case 1:
                    return therapistAppointmentTimeRepository.findAll(splitFio[0],"","",pageable);
                case 2:
                    return therapistAppointmentTimeRepository.findAll(splitFio[0],splitFio[1],"",pageable);
                case 3:
                    return therapistAppointmentTimeRepository.findAll(splitFio[0],splitFio[1],splitFio[2],pageable);
            }
        }
        return therapistAppointmentTimeRepository.findAll(pageable);
    }

}
