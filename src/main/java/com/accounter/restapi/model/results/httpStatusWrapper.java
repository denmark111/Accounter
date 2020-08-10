package com.accounter.restapi.model.results;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class httpStatusWrapper {

    String statusCode;
    String statusMessage;

    Object goCompanyResult;
}
