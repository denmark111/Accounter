package com.accounter.restapi.model.results;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Setter
@Getter
@ToString
public class GoAccountSubjectResult {

    Long accountId;

    String accountSystem;

    String accountSubjectNameDetail;

    String accountSubjectName;

    String relationAccountSubjectNameDetail;

    Long division;

    Long accountCode;

    Long relationCode;

    Date createdAt;

    Date modifiedAt;

    Date deletedAt;
}
