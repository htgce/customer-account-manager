package com.abnambro.customer.account.manager.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LogonRequest {
    private String userName;
    private String password;
}
