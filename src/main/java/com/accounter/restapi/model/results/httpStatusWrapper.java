package com.accounter.restapi.model.results;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class httpStatusWrapper {

    // HTTP 상태를 보여주기 위한 Wrapper입니다.

    // 200, 400, 404 같은 상태코드 포함
    String statusCode;
    // Not Found 같은 상태 메시지 포함
    String statusMessage;

    // 같이 반환될 결과 오브젝트
    Object returnResult;
}
