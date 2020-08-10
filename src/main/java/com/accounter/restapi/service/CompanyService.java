package com.accounter.restapi.service;

import com.accounter.restapi.model.entities.GoAccountSubjectEntity;
import com.accounter.restapi.model.entities.GoCompanyEntity;
import com.accounter.restapi.model.params.GoCompanyParam;
import com.accounter.restapi.model.results.GoCompanyResult;
import com.accounter.restapi.repository.GoAccountSubjectRepo;
import com.accounter.restapi.repository.GoCompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    @Autowired
    GoCompanyRepo goCompanyRepo;

    @Autowired
    GoAccountSubjectRepo goAccountSubjectRepo;

    @Transactional
    public List<GoCompanyResult> getCompany() {
        List<GoCompanyEntity> companyList = goCompanyRepo.findAll();
        List<GoCompanyResult> results = companyList.stream().map(goCompanyEntity -> {
            GoCompanyResult companyResult = new GoCompanyResult();

            companyResult.setCompanyId(goCompanyEntity.getCompanyId());
            companyResult.setCompanyName(goCompanyEntity.getCompanyName());
            companyResult.setServiceType(goCompanyEntity.getServiceType());

            return companyResult;
        }).collect(Collectors.toList());

        return results;
    }

    @Transactional
    public Object getCompany(Long cid) {
        return goCompanyRepo.findById(cid).map(goCompanyEntity -> {
            GoCompanyResult companyResult = new GoCompanyResult();
            List<GoAccountSubjectEntity> accountList = goAccountSubjectRepo.findByDivision(cid);

            companyResult.setAccountCount(accountList.size());
            companyResult.setAccounts(accountList);
            companyResult.setCompanyName(goCompanyEntity.getCompanyName());
            companyResult.setServiceType(goCompanyEntity.getServiceType());

            return companyResult;
        });
    }

    @Transactional
    public void add(GoCompanyParam param) {
        GoCompanyEntity gce = new GoCompanyEntity();

        gce.setCompanyName(param.getCompanyName());
        gce.setServiceType(param.getServiceType());

        goCompanyRepo.save(gce);
    }

    @Transactional
    public void edit(GoCompanyParam param) {
        Optional<GoCompanyEntity> getEntity = goCompanyRepo.findById(param.getCompanyId());
        getEntity.ifPresent(goCompanyEntity -> {



            goCompanyRepo.save(goCompanyEntity);
        });
    }

    @Transactional
    public void delete(Long cid) {
        goCompanyRepo.deleteById(cid);
    }

}
