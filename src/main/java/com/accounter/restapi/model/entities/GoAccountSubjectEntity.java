package com.accounter.restapi.model.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

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
