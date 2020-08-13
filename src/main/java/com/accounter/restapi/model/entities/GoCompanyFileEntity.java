package com.accounter.restapi.model.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GoCompanyFileEntity {

    private String companyName;
    private String serviceType;

    private Long usersTotal;
    private Long usersDeleted;
    private Long usersUse;
    private Long connectionId;

    private Boolean serviceInUse;
    private Boolean otherService;

}
