package com.accounter.restapi.service;

import com.accounter.restapi.model.entities.GoAccountSubjectEntity;
import com.accounter.restapi.model.entities.GoCompanyEntity;
import com.accounter.restapi.model.params.GoCompanyParam;
import com.accounter.restapi.model.results.GoCompanyResult;
import com.accounter.restapi.model.results.httpStatusWrapper;
import com.accounter.restapi.repository.GoAccountSubjectRepo;
import com.accounter.restapi.repository.GoCompanyRepo;
import org.springframework.beans.BeanUtils;
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

    // 기업이 가진 계정정보를 불러오려면 Account 정보에도 접근이 필요함
    @Autowired
    GoAccountSubjectRepo goAccountSubjectRepo;

    // DB의 모든 기업 정보를 반환
    // 반환 시 httpStatusWrapper에 감싸 HTTP 상태 표시
    @Transactional
    public httpStatusWrapper getCompany() {

        httpStatusWrapper hsw = new httpStatusWrapper();

        // 다수의 기업정보를 반환하기 때문에 리스트로 저장
        List<GoCompanyEntity> companyList = goCompanyRepo.findAll();

        // DB에서 호출한 데이터는 Entity 객체이기 때문에
        // Controller에 전달할 별도의 객체에 값 옮기기
        List<GoCompanyResult> results = companyList.stream().map(goCompanyEntity -> {
            GoCompanyResult companyResult = new GoCompanyResult();
            List<GoAccountSubjectEntity> accountList = goAccountSubjectRepo.findAll();

            BeanUtils.copyProperties(goCompanyEntity, companyResult);
            companyResult.setAccountCount(accountList.size());
            companyResult.setAccounts(accountList);

            return companyResult;
        }).collect(Collectors.toList());

        // HTTP 상태 메시지 포함 후 결과 반환
        hsw.setStatusCode("200");
        hsw.setStatusMessage("OK");
        hsw.setReturnResult(results);

        return hsw;
    }

    // DB에 저장된 기업 중 특정 ID를 가진 기업정보 1개를 조회
    // 인자로 받는 cid는 기업정보의 고유 ID
    // CompanyService.getCompany()와 매우 유사하지만 단일 기업정보 조회를 목적으로 하기에
    // 별도의 리스트가 아닌 GoCompanyResult 객체에 담아서 결과 반환
    @Transactional
    public httpStatusWrapper getCompany(Long cid) {

        httpStatusWrapper hsw = new httpStatusWrapper();

        hsw.setReturnResult(goCompanyRepo.findById(cid).map(goCompanyEntity -> {
            GoCompanyResult companyResult = new GoCompanyResult();
            List<GoAccountSubjectEntity> accountList = goAccountSubjectRepo.findByDivision(cid);

            BeanUtils.copyProperties(goCompanyEntity, companyResult);
            companyResult.setAccountCount(accountList.size());
            companyResult.setAccounts(accountList);

            return companyResult;
        }));

        hsw.setStatusCode("200");
        hsw.setStatusMessage("OK");

        return hsw;
    }

    // Controller에서 전달받은 기업정보(GoCompanyParam 객체로 전달)를 DB에 신규로 추가
    @Transactional
    public void add(GoCompanyParam param) {

        // Controller에서 넘어오는 param 객체에서 entity 객체로의 값 이동 필요
        GoCompanyEntity gce = new GoCompanyEntity();
        BeanUtils.copyProperties(param, gce);

        // 이동이 끝나면 DB에 저장
        goCompanyRepo.save(gce);
    }

    // Controller에서 전달받은 기업정보를 DB에서 수정 / 업데이트
    // CompanyService.add(GoCompanyParam param)와 매우 유사
    @Transactional
    public void edit(GoCompanyParam param) {
        Optional<GoCompanyEntity> getEntity = goCompanyRepo.findById(param.getCompanyId());
        getEntity.ifPresent(gce -> {

            // 우선 입력받은 기업정보가 이미 DB에 존재하는지 체크 후
            // 있다면 값을 넣고 없다면 그냥 스킵
            BeanUtils.copyProperties(param, gce);
            goCompanyRepo.save(gce);
        });
    }

    // 기업 ID로 DB에서 값을 삭제
    @Transactional
    public void delete(Long cid) {
        goCompanyRepo.deleteById(cid);
    }

}
