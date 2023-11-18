package com.polyclinic.mis.service.impl;

import com.polyclinic.mis.models.AnalysisCabinet;
import com.polyclinic.mis.models.DoctorCabinet;
import com.polyclinic.mis.repository.AnalysisCabinetRepository;
import com.polyclinic.mis.repository.DoctorCabinetRepository;
import com.polyclinic.mis.service.AnalysisCabinetService;
import com.polyclinic.mis.service.DoctorCabinetService;
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
public class DoctorCabinetServiceImpl implements DoctorCabinetService {
    @Autowired
    private DoctorCabinetRepository doctorCabinetRepository;
    @Override
    public DoctorCabinet add(DoctorCabinet doctorCabinet) {
        return doctorCabinetRepository.saveAndFlush(doctorCabinet);
    }
    @Override
    public Optional<DoctorCabinet> getById(Long id){
        return doctorCabinetRepository.findById(id);
    }
    @Override
    public void delete(Long id) {
        doctorCabinetRepository.deleteById(id);
    }

    @Override
    public DoctorCabinet edit(DoctorCabinet analysisCabinet) {
        return doctorCabinetRepository.saveAndFlush(analysisCabinet);
    }

    @Override
    public List<DoctorCabinet> getAll() {
        return doctorCabinetRepository.findAll();
    }

    @Override
    public Page<DoctorCabinet> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection, String name) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        if (name.equals("")){
            return doctorCabinetRepository.findAll(pageable);
        }
        else{
            return doctorCabinetRepository.findAll(name,pageable);
            }
    }

}
