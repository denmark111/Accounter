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
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    @Transactional
    public httpStatusWrapper importCsv(GoFileParam param) {
        httpStatusWrapper hsw = new httpStatusWrapper();

        hsw.setStatusCode("200");
        hsw.setStatusMessage("OK");

        int batch_size = 100;

        try (CSVReader reader = new CSVReader(
                                new BufferedReader(
                                new InputStreamReader(param.getAccount()                                                                                                                                                                                                                                                                                                                                                                                                                                                    .getInputStream())))) {

            CsvToBean<GoAccountFileEntity> accountList = new CsvToBeanBuilder(reader)
                    .withType(GoAccountFileEntity.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            Iterator<GoAccountFileEntity> gafeIter = accountList.iterator();

            List<GoAccountSubjectEntity> acl = new ArrayList();
            while(gafeIter.hasNext()) {

                GoAccountSubjectEntity gase = new GoAccountSubjectEntity();
                BeanUtils.copyProperties(gafeIter.next(), gase);
                acl.add(gase);

                if (acl.size() > batch_size) {
                    goAccountSubjectRepo.saveAll(acl);
                    acl.clear();
                }
            }
            goAccountSubjectRepo.saveAll(acl);
        }
        catch (IOException ie) {
            ie.printStackTrace();
        }



        try (CSVReader reader = new CSVReader(
                                new BufferedReader(
                                new InputStreamReader(param.getCompany()                                                                                                                                                                                                                                                                                                                                                                                                                                                    .getInputStream())))) {

            CsvToBean<GoCompanyFileEntity> companyList = new CsvToBeanBuilder(reader)
                    .withType(GoCompanyFileEntity.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            Iterator<GoCompanyFileEntity> gcfeIter = companyList.iterator();

            List<GoCompanyEntity> cpl = new ArrayList();
            while(gcfeIter.hasNext()) {

                GoCompanyEntity gce = new GoCompanyEntity();
                BeanUtils.copyProperties(gcfeIter.next(), gce);
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
        }

        return hsw;
    }

}