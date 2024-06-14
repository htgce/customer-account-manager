package com.abnambro.customer.account.manager.web;

import com.abnambro.customer.account.manager.service.AccountService;
import com.abnambro.customer.account.manager.service.LogonService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping("/api/v1/account")
@Tag(name = "Account", description = "Account API")
@RestController
public class AccountController implements AccountApi {

    private final AccountService accountService;

    @Autowired
    public AccountController(final AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(value = "/overview")
    public ResponseEntity<?> overview() {
         return new ResponseEntity<>(accountService.getOverview(), HttpStatusCode.valueOf(200));
    }
}
