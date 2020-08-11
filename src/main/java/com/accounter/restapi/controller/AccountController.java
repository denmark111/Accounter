package com.accounter.restapi.controller;

import com.accounter.restapi.model.params.GoAccountSubjectParam;
import com.accounter.restapi.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping(
            value = "/all",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity get() {
        return ResponseEntity.ok(accountService.getAccount());
    }

    @GetMapping(
            value = "/{aid}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity get(@PathVariable("aid") Long aid) {
        return ResponseEntity.ok(accountService.getAccount(aid));
    }

    @PostMapping(
            value = "/add",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity add(@RequestBody @Valid GoAccountSubjectParam param) {
        accountService.add(param);

        return ResponseEntity.ok(null);
    }

    @PutMapping(
            value = "/{aid}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity edit(@RequestBody @Valid GoAccountSubjectParam param,
                               @PathVariable("aid") Long aid) {

        param.setAccountId(aid);
        accountService.edit(param);

        return ResponseEntity.ok(null);
    }

    @DeleteMapping(
            value = "/{aid}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity delete(@PathVariable("aid") Long aid) {
        accountService.delete(aid);

        return ResponseEntity.ok(null);
    }
}
