package com.polyclinic.mis.repos;
import java.util.List;

import com.polyclinic.mis.models.Analysis;
import org.springframework.data.repository.CrudRepository;
public interface AnalysisRepository extends CrudRepository<Analysis, Long> {
    Analysis findById(long id);

}
