package com.accounter.restapi.repository;

import com.accounter.restapi.model.entities.GoAccountSubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoAccountSubjectRepo extends JpaRepository<GoAccountSubjectEntity, Long> {

    // 추후 기업 조회 시 기업이 가진 모든 계정정보도 불러오기 위해 추가됨
    List<GoAccountSubjectEntity> findByDivision(Long companyCode);

}
