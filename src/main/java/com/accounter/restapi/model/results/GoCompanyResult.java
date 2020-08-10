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

    List<GoAccountSubjectEntity> accounts;

    Date createdAt;
    Date modifiedAt;
    Date deletedAt;

}
