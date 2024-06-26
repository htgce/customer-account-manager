package com.abnambro.customer.account.manager.web;

import com.abnambro.customer.account.manager.model.request.RegistrationRequest;
import com.abnambro.customer.account.manager.model.response.RegistrationResponse;
import com.abnambro.customer.account.manager.service.RegistrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/register")
@Tag(name = "Register", description = "Registration API")
@RestController
public class RegistrationController implements RegistrationApi {
    private static final Logger LOG = LoggerFactory.getLogger(RegistrationController.class);

    private RegistrationService registrationService;

    @Autowired
    public RegistrationController(final RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Register a new customer", description = "Registers a new customer and returns a success message.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "User registered successfully",
                            content = @Content(schema = @Schema(implementation = RegistrationResponse.class)))
            })
    public ResponseEntity<RegistrationResponse> register(@Valid @RequestBody RegistrationRequest request) {
        return new ResponseEntity<>(registrationService.register(request), HttpStatus.CREATED);
    }
}
