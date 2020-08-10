package com.accounter.restapi.model.results;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class httpStatusWrapper {

    String statusCode;
    String statusMessage;

    GoCompanyResult goCompanyResult;
}
