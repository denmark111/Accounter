package com.accounter.restapi.model.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;


// DB에 저장 될 go_account_subject Table을 정의합니다.
// JPA 객체이므로 기본적인 Column 데이터 형태를 선언합니다.
// DB에서 값을 불러올 때 사용될 객체입니다.


@Getter
@Setter
@ToString
@Entity
@Table(name = "go_account_subject")
public class GoAccountSubjectEntity {

    @Id
    @GeneratedValue
    private Long accountId;

    private String accountSystem;
    private String accountSubjectNameDetail;
    private String accountSubjectName;
    private String relationAccountSubjectNameDetail;

    private Long division;
    private Long accountCode;
    private Long relationCode;

    private Date createdAt;
    private Date modifiedAt;
    private Date deletedAt;

}
