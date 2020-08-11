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
import java.util.List;

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
