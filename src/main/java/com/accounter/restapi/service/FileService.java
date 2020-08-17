package com.accounter.restapi.service;

import com.accounter.restapi.model.entities.GoAccountFileEntity;
import com.accounter.restapi.model.entities.GoAccountSubjectEntity;
import com.accounter.restapi.model.entities.GoCompanyEntity;
import com.accounter.restapi.model.entities.GoCompanyFileEntity;
import com.accounter.restapi.model.params.GoFileParam;
import com.accounter.restapi.model.results.httpStatusWrapper;
import com.accounter.restapi.repository.GoAccountSubjectRepo;
import com.accounter.restapi.repository.GoCompanyRepo;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class FileService {

    @Autowired
    private GoCompanyRepo goCompanyRepo;

    @Autowired
    private GoAccountSubjectRepo goAccountSubjectRepo;

    // 계정정보 및 회사정보 읽기
    // 데이터의 의존성으로 인해 계정정보를 먼저 읽은 후,
    // 회사정보를 읽어 서로 연결함
    @Transactional
    public httpStatusWrapper importCsv(GoFileParam param) {
        httpStatusWrapper hsw = new httpStatusWrapper();

        List<String> status = new ArrayList();

        hsw.setStatusCode("200");
        hsw.setStatusMessage("OK");

        // 한번에 DB에 저장할 계정정보의 갯수
        int batch_size = 5000;

        try (CSVReader reader = new CSVReader(
                                new BufferedReader(
                                new InputStreamReader(param.getAccount().getInputStream())))) {

            CsvToBean<GoAccountFileEntity> accountList = new CsvToBeanBuilder(reader)
                    .withType(GoAccountFileEntity.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            // CSV 파일을 한번에 읽지 않고 Iterator를 이용하여 한개씩 순회
            Iterator<GoAccountFileEntity> gafeIter = accountList.iterator();

            List<GoAccountSubjectEntity> acl = new ArrayList();
            while(gafeIter.hasNext()) {

                // 만약 Iterator에 다음 항목이 있다면
                GoAccountSubjectEntity gase = new GoAccountSubjectEntity();
                // DB 엔터티 형식으로 변환 후
                BeanUtils.copyProperties(gafeIter.next(), gase);
                // 큐에 저장
                acl.add(gase);

                if (acl.size() > batch_size) {
                    // DB Batch 수행
                    goAccountSubjectRepo.saveAll(acl);
                    acl.clear();
                }
            }
            goAccountSubjectRepo.saveAll(acl);
        }
        // 예외 발생 시 아직 별다른 처리 없이 콘솔에만 표시 후 넘어감
        catch (IOException ie) {
            ie.printStackTrace();
            status.add("[IOException]" + param.getAccount().getOriginalFilename() + " => Failed to read");
        }



        // 회사정보 읽기
        try (CSVReader reader = new CSVReader(
                                new BufferedReader(
                                new InputStreamReader(param.getCompany().getInputStream())))) {

            CsvToBean<GoCompanyFileEntity> companyList = new CsvToBeanBuilder(reader)
                    .withType(GoCompanyFileEntity.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            Iterator<GoCompanyFileEntity> gcfeIter = companyList.iterator();

            List<GoCompanyEntity> cpl = new ArrayList();
            while(gcfeIter.hasNext()) {

                GoCompanyEntity gce = new GoCompanyEntity();
                BeanUtils.copyProperties(gcfeIter.next(), gce);
                // 계정정보와 유사하지만 미리 저장되어 있는 계정정보를 이용해
                // 회사정보와의 연관성을 확보 후 저장
                gce.setGoAccountSubjectEntity(goAccountSubjectRepo.findByCompanyName(gce.getCompanyName()));
                cpl.add(gce);

                if (cpl.size() > batch_size) {
                    goCompanyRepo.saveAll(cpl);
                    cpl.clear();
                }
            }
            goCompanyRepo.saveAll(cpl);
        }
        catch (IOException ie) {
            ie.printStackTrace();
            status.add("[IOException]" + param.getCompany().getOriginalFilename() + " => Failed to read");
        }

        // HTTP 상태정보를 담고있는 객체에 감싼 후 외부로 전달
        hsw.setReturnResult(status);

        return hsw;
    }
}