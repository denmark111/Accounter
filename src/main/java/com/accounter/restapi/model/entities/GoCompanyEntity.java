package com.accounter.restapi.model.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;


// DB에 저장 될 go_company Table을 정의합니다.
// JPA 객체이므로 기본적인 Column 데이터 형태를 선언합니다.
// DB에서 값을 불러올 때 사용될 객체입니다.


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
