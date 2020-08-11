package com.accounter.restapi.model.results;

import com.accounter.restapi.model.entities.GoAccountSubjectEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;


// GET, POST 요청에 주로 사용되는 객체입니다.
// Service -> Controller 로 값이 이동할 때 전달됩니다.
// API 요청 시 반환되는 데이터의 형태를 결정합니다.


@Getter
@Setter
@ToString
public class GoCompanyResult {

    Long companyId;

    Long connectionId;
    Long usersTotal;
    Long usersUse;

    int accountCount;

    Boolean serviceInUse;
    Boolean otherService;

    String companyName;
    String serviceType;

    // GoCompanyEntity 와는 다르게
    // 기업 정보를 반환할 때 해당 기업이 가진 계정정보도 모두 표시해야 하므로
    // 리스트 형태의 계정 오브젝트를 포함함.
    List<GoAccountSubjectEntity> accounts;

    Date createdAt;
    Date modifiedAt;
    Date deletedAt;

}
