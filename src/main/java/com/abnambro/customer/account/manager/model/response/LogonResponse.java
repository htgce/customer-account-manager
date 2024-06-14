package com.abnambro.customer.account.manager.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LogonResponse {
    private String userName;
    private String token;
}
