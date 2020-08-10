package com.accounter.restapi.service;

import com.accounter.restapi.model.entities.GoAccountSubjectEntity;
import com.accounter.restapi.model.params.GoAccountSubjectParam;
import com.accounter.restapi.model.results.GoAccountSubjectResult;
import com.accounter.restapi.repository.GoAccountSubjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService {

    @Autowired
    GoAccountSubjectRepo goAccountSubjectRepo;

    @Transactional
    public List<GoAccountSubjectResult> getAccount() {
        List<GoAccountSubjectEntity> accountList = goAccountSubjectRepo.findAll();
        List<GoAccountSubjectResult> results = accountList.stream().map(goAccountSubjectEntity -> {
            GoAccountSubjectResult accountResult = new GoAccountSubjectResult();

            accountResult.setDivision(goAccountSubjectEntity.getDivision());
            accountResult.setAccountId(goAccountSubjectEntity.getAccountId());
            accountResult.setAccountSubjectName(goAccountSubjectEntity.getAccountSubjectName());
            accountResult.setAccountCode(goAccountSubjectEntity.getAccountCode());
            accountResult.setRelationCode(goAccountSubjectEntity.getRelationCode());

            return accountResult;
        }).collect(Collectors.toList());

        return results;
    }

    @Transactional
    public void add(GoAccountSubjectParam param) {
        GoAccountSubjectEntity gase = new GoAccountSubjectEntity();

        gase.setAccountSubjectName(param.getAccountSubjectName());
        gase.setAccountCode(param.getAccountCode());
        gase.setRelationCode(param.getRelationCode());
        gase.setDivision(param.getDivision());

        goAccountSubjectRepo.save(gase);
    }

    @Transactional
    public void edit(GoAccountSubjectParam param) {
        Optional<GoAccountSubjectEntity> getEntity = goAccountSubjectRepo.findById(param.getAccountId());
        getEntity.ifPresent(entity -> {

            entity.setAccountSubjectName(param.getAccountSubjectName());
            entity.setAccountCode(param.getAccountCode());
            entity.setRelationCode(param.getRelationCode());

            goAccountSubjectRepo.save(entity);
        });
    }

    @Transactional
    public void delete(Long aid) {
        goAccountSubjectRepo.deleteById(aid);
    }

}
