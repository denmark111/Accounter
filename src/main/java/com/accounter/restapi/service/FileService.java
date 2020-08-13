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
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {

    @Autowired
    private GoCompanyRepo goCompanyRepo;

    @Autowired
    private GoAccountSubjectRepo goAccountSubjectRepo;

    private <T> List<T> beanFromCsv(final MultipartFile file, final Class<T> beanClass) {

        try (CSVReader reader = new CSVReader(new BufferedReader(new InputStreamReader(file.getInputStream())))) {

            HeaderColumnNameMappingStrategy<T> mappingStrategy = new HeaderColumnNameMappingStrategy<T>();
            mappingStrategy.setType(beanClass);

            CsvToBean<T> csvToBean = new CsvToBean<T>();
            csvToBean.setMappingStrategy(mappingStrategy);
            csvToBean.setCsvReader(reader);

            return csvToBean.parse();

        }
        catch (IOException e) {
            e.printStackTrace();

            return null;
        }
    }

    @Transactional
    public httpStatusWrapper importCsv(GoFileParam param) {
        httpStatusWrapper hsw = new httpStatusWrapper();

        hsw.setStatusCode("200");
        hsw.setStatusMessage("OK");

        List<String> status = new ArrayList();

        List<GoAccountSubjectEntity> accountEntities = new ArrayList();
        List<GoAccountFileEntity> accountList = beanFromCsv(param.getAccount(), GoAccountFileEntity.class);

        if (accountList == null) {
            status.add(param.getAccount().getOriginalFilename() + " => Failed to read");
        }
        else {
            for(GoAccountFileEntity account:accountList) {
                GoAccountSubjectEntity gase = new GoAccountSubjectEntity();
                BeanUtils.copyProperties(account, gase);
                accountEntities.add(gase);
            }
            goAccountSubjectRepo.saveAll(accountEntities);
        }

        List<GoCompanyEntity> companyEntities = new ArrayList();
        List<GoCompanyFileEntity> companyList = beanFromCsv(param.getCompany(), GoCompanyFileEntity.class);

        if (companyList == null) {
            status.add(param.getCompany().getOriginalFilename() + " => Failed to read");
        }
        else {
            for(GoCompanyFileEntity company:companyList) {
                GoCompanyEntity gce = new GoCompanyEntity();
                BeanUtils.copyProperties(company, gce);
                gce.setGoAccountSubjectEntity(goAccountSubjectRepo.findByCompanyName(gce.getCompanyName()));
                companyEntities.add(gce);
            }
            goCompanyRepo.saveAll(companyEntities);
        }

        hsw.setReturnResult(status);

        return hsw;
    }

}