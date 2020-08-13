package com.accounter.restapi.model.entities;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GoAccountFileEntity {

    @CsvBindByName(column = "회사명")
    private String companyName;

    @CsvBindByName(column = "계정체계")
    private String accountSystem;

    @CsvBindByName(column = "과목명(목)")
    private String accountSubjectName;

    @CsvBindByName(column = "과목명(세목)")
    private String accountSubjectNameDetail;

    @CsvBindByName(column = "분류")
    private String accountType;

    @CsvBindByName(column = "관계계정과목명")
    private String relationAccountSubjectNameDetail;

    @CsvBindByName(column = "계정코드")
    private Long accountCode;

    @CsvBindByName(column = "관계코드")
    private Long relationCode;

}
