package com.accounter.restapi.model.results;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


// GET, POST 요청에 주로 사용되는 객체입니다.
// Service -> Controller 로 값이 이동할 때 전달됩니다.
// API 요청 시 반환되는 데이터의 형태를 결정합니다.


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
