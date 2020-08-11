package com.accounter.restapi.model.results;

import com.accounter.restapi.model.entities.GoAccountSubjectEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

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
