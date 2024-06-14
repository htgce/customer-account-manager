package com.abnambro.customer.account.manager.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistrationRequest {
    String userName;
    String name;
    String address;
    String dateOfBirth;
    String idDocNumber;
}
