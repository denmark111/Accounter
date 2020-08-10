package com.accounter.restapi.service;

import com.accounter.restapi.model.entities.GoAccountSubjectEntity;
import com.accounter.restapi.model.params.GoAccountSubjectParam;
import com.accounter.restapi.model.results.GoAccountSubjectResult;
import com.accounter.restapi.repository.GoAccountSubjectRepo;
import org.springframework.beans.BeanUtils;
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
            BeanUtils.copyProperties(goAccountSubjectEntity, accountResult);

            return accountResult;
        }).collect(Collectors.toList());

        return results;
    }

    @Transactional
    public void add(GoAccountSubjectParam param) {
        GoAccountSubjectEntity gase = new GoAccountSubjectEntity();
        BeanUtils.copyProperties(param, gase);
        goAccountSubjectRepo.save(gase);
    }

    @Transactional
    public void edit(GoAccountSubjectParam param) {
        Optional<GoAccountSubjectEntity> getEntity = goAccountSubjectRepo.findById(param.getAccountId());
        getEntity.ifPresent(entity -> {
            BeanUtils.copyProperties(param, entity);
            goAccountSubjectRepo.save(entity);
        });
    }

    @Transactional
    public void delete(Long aid) {
        goAccountSubjectRepo.deleteById(aid);
    }

}
