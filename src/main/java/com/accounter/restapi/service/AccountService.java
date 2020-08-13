package com.accounter.restapi.service;

import com.accounter.restapi.model.entities.GoAccountSubjectEntity;
import com.accounter.restapi.model.params.GoAccountSubjectParam;
import com.accounter.restapi.model.results.GoAccountSubjectResult;
import com.accounter.restapi.model.results.httpStatusWrapper;
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

    // JPA 데이터 저장 Repository
    @Autowired
    private GoAccountSubjectRepo goAccountSubjectRepo;

    // DB의 모든 계정 정보를 반환
    // 반환 시 httpStatusWrapper에 감싸 HTTP 상태 표시
    @Transactional
    public httpStatusWrapper getAccount() {

        httpStatusWrapper hsw = new httpStatusWrapper();

        // 다수의 계정정보를 반환하기 때문에 리스트로 저장
        List<GoAccountSubjectEntity> accountList = goAccountSubjectRepo.findAll();

        // DB에서 호출한 데이터는 Entity 객체이기 때문에
        // Controller에 전달할 별도의 객체에 값 옮기기
        List<GoAccountSubjectResult> results = accountList.stream().map(goAccountSubjectEntity -> {
            GoAccountSubjectResult accountResult = new GoAccountSubjectResult();
            BeanUtils.copyProperties(goAccountSubjectEntity, accountResult);

            return accountResult;
        }).collect(Collectors.toList());

        // HTTP 상태 메시지 포함 후 결과 반환
        hsw.setStatusCode("200");
        hsw.setStatusMessage("OK");
        hsw.setReturnResult(results);

        return hsw;
    }

    // DB에 저장된 기업 중 특정 ID를 가진 계정정보 1개를 조회
    // 인자로 받는 aid는 계정정보의 고유 ID
    // AccountService.getAccount()와 매우 유사하지만 단일 계정보 조회를 목적으로 하기에
    // 별도의 리스트가 아닌 GoAccountSubjectResult 객체에 담아서 결과 반환
    @Transactional
    public httpStatusWrapper getAccount(Long aid) {

        httpStatusWrapper hsw = new httpStatusWrapper();

        hsw.setReturnResult(goAccountSubjectRepo.findById(aid).map((goAccountSubjectEntity -> {
            GoAccountSubjectResult goAccountSubjectResult = new GoAccountSubjectResult();

            BeanUtils.copyProperties(goAccountSubjectEntity, goAccountSubjectResult);

            return goAccountSubjectResult;
        })));

        hsw.setStatusCode("200");
        hsw.setStatusMessage("OK");

        return hsw;

    }

    // Controller에서 전달받은 계정정보(GoAccountSubjectParam 객체로 전달)를 DB에 신규로 추가
    @Transactional
    public void add(GoAccountSubjectParam param) {

        // Controller에서 넘어오는 param 객체에서 entity 객체로의 값 이동 필요
        GoAccountSubjectEntity gase = new GoAccountSubjectEntity();
        BeanUtils.copyProperties(param, gase);

        // 이동이 끝나면 DB에 저장
        goAccountSubjectRepo.save(gase);
    }

    // Controller에서 전달받은 계정정보를 DB에서 수정 / 업데이트
    // AccountService.add(GoAccountSubjectParam param)와 매우 유사
    @Transactional
    public void edit(GoAccountSubjectParam param) {
        Optional<GoAccountSubjectEntity> getEntity = goAccountSubjectRepo.findById(param.getAccountId());
        getEntity.ifPresent(entity -> {
            // 우선 입력받은 계정정보가 이미 DB에 존재하는지 체크 후
            // 있다면 값을 넣고 없다면 그냥 스킵
            BeanUtils.copyProperties(param, entity, "accountId");
            goAccountSubjectRepo.save(entity);
        });
    }

    // 계정 ID로 DB에서 값을 삭제
    @Transactional
    public void delete(Long aid) {
        goAccountSubjectRepo.deleteById(aid);
    }

}
