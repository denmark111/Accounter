package com.accounter.restapi.repository;

import com.accounter.restapi.model.entities.GoAccountSubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoAccountSubjectRepo extends JpaRepository<GoAccountSubjectEntity, Long> {

    List<GoAccountSubjectEntity> findByDivision(Long companyCode);

}
