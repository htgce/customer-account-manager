package com.abnambro.customer.account.manager.model.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ErrorResponse {
    HttpStatus status;
    String message;
    Integer statusCode;
   }
