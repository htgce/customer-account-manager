package com.abnambro.customer.account.manager.web;

import com.abnambro.customer.account.manager.model.request.LogonRequest;
import org.springframework.http.ResponseEntity;

public interface LogonApi {
    ResponseEntity<?>  logon(LogonRequest request);
}
