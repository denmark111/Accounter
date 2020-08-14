package com.accounter.restapi.model.entities;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GoCompanyFileEntity {

    @CsvBindByName(column = "회사명")
    private String companyName;

    @CsvBindByName(column = "연동시스템 종류")
    private String serviceType;


    @CsvBindByName(column = "사용자 총 계정수")
    private Long usersTotal;

    @CsvBindByName(column = "삭제 계정수")
    private Long usersDeleted;

    @CsvBindByName(column = "사용 계정수")
    private Long usersUse;

    @CsvBindByName(column = "연동 ID")
    private Long connectionId;


    @CsvBindByName(column = "서비스 사용유무")
    private Boolean serviceInUse;

    @CsvBindByName(column = "타시스템 연동 여부")
    private Boolean otherService;

}
