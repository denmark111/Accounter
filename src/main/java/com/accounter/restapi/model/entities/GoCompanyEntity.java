package com.accounter.restapi.model.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "go_company")
public class GoCompanyEntity {

    @Id
    @GeneratedValue
    private Long companyId;

    private Long connectionId;
    private Long usersTotal;
    private Long usersUse;

    private int accountCount;

    private Boolean serviceInUse;
    private Boolean otherService;

    private String companyName;
    private String serviceType;

    private Date createdAt;
    private Date modifiedAt;
    private Date deletedAt;

}
