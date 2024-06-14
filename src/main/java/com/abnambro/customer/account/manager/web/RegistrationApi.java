package com.abnambro.customer.account.manager.web;

import com.abnambro.customer.account.manager.exception.user.UserExistsException;
import com.abnambro.customer.account.manager.model.request.RegistrationRequest;
import com.abnambro.customer.account.manager.model.response.RegistrationResponse;
import org.springframework.http.ResponseEntity;

public interface RegistrationApi {
    ResponseEntity<RegistrationResponse> register(RegistrationRequest request) throws UserExistsException;
}
