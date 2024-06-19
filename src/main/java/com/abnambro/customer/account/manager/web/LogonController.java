package com.abnambro.customer.account.manager.web;

import com.abnambro.customer.account.manager.model.request.LogonRequest;
import com.abnambro.customer.account.manager.model.response.LogonResponse;
import com.abnambro.customer.account.manager.service.AccountService;
import com.abnambro.customer.account.manager.service.LogonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping("/api/v1/logon")
@Tag(name = "Logon", description = "Logon API")
@RestController
public class LogonController implements LogonApi {
    private static final Logger LOG = LoggerFactory.getLogger(RegistrationController.class);

    private final AccountService accountService;

    private LogonService logonService;

    @Autowired
    public LogonController(final LogonService logonService,AccountService accountService) {
        this.logonService = logonService;
        this.accountService = accountService;
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    @Operation(summary = "Logon customer", description = "Logs in a customer and returns JWT tokens.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Customer logged in successfully",
                            content = @Content(schema = @Schema(implementation = LogonResponse.class)))
            })
    public ResponseEntity<?> logon(@Valid @RequestBody LogonRequest loginRequest) {
        LogonResponse response = logonService.logon(loginRequest);
        return ResponseEntity.ok()
                .header(
                        HttpHeaders.AUTHORIZATION,
                        response.getToken()
                ).body(response);
    }
}
