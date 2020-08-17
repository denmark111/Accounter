package com.accounter.restapi.controller;

import com.accounter.restapi.model.params.GoFileParam;
import com.accounter.restapi.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/upload")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping(
            value = "/"
    )
    public ResponseEntity uploadAccount(@Valid GoFileParam param) {
        if (!param.getCompany().getContentType().equals("text/csv") ||
            param.getCompany().isEmpty()) {
            return ResponseEntity.ok(null);
        }
        else if (!param.getAccount().getContentType().equals("text/csv") ||
                param.getAccount().isEmpty()) {
            return ResponseEntity.ok(null);
        }
        else {
            return ResponseEntity.ok(fileService.importCsv(param));
        }
    }

}
