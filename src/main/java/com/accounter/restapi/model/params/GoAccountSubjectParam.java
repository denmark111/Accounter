package com.accounter.restapi.model.params;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@ToString
@Builder
public class GoAccountSubjectParam {

    @Min(0)
    Long accountId;

    String accountSystem;

    String accountSubjectNameDetail;

    @NotEmpty
    String accountSubjectName;

    String relationAccountSubjectNameDetail;

    Long division;

    @NotNull
    Long accountCode;

    @NotNull
    Long relationCode;

    Date createdAt;

    Date modifiedAt;

    Date deletedAt;

}
