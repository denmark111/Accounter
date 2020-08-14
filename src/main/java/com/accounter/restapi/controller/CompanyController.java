package com.accounter.restapi.controller;

import com.accounter.restapi.model.params.GoCompanyParam;
import com.accounter.restapi.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping(
            value = "/all",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity get() {
        return ResponseEntity.ok(companyService.getCompany());
    }

    @GetMapping(
            value = "/{cid}/list",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity get(@PathVariable("cid") Long cid) {
        return ResponseEntity.ok(companyService.getCompany(cid));
    }

    @PostMapping(
            value = "/add",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity add(@RequestBody @Valid GoCompanyParam param) {
        companyService.add(param);

        return ResponseEntity.ok(null);
    }

    @PutMapping(
            value = "/{cid}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity edit(@PathVariable("cid") Long cid,
                               @RequestBody @Valid GoCompanyParam param) {
        companyService.edit(cid, param);

        return ResponseEntity.ok(null);
    }

    @DeleteMapping(value = "/{cid}")
    public ResponseEntity delete(@PathVariable("cid") Long cid) {
        companyService.delete(cid);

        return ResponseEntity.ok(null);
    }


}
