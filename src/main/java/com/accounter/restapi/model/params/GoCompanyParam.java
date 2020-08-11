package com.accounter.restapi.model.params;

import com.accounter.restapi.model.entities.GoAccountSubjectEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;


// POST, PUT 의 경우에 사용됩니다.
// Controller에서 값을 받을 객체를 정의합니다.
// Controller -> Service 로의 값의 이동에 사용되는 객체입니다.
// API를 통해 입력되는 값들의 제약사항을 담고있습니다.


@Getter
@Setter
@ToString
@Builder
public class GoCompanyParam {

    @Min(0)
    Long companyId;

    Long connectionId;
    Long usersTotal;
    Long usersUse;

    int accountCount;

    Boolean serviceInUse;
    Boolean otherService;

    @NotEmpty
    String companyName;

    @NotEmpty
    String serviceType;

    Date createdAt;
    Date modifiedAt;
    Date deletedAt;

}
