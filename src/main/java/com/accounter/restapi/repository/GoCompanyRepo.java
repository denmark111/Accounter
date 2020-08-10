package com.accounter.restapi.repository;

import com.accounter.restapi.model.entities.GoCompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoCompanyRepo extends JpaRepository<GoCompanyEntity, Long> {
}
