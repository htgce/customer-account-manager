package com.abnambro.customer.account.manager.service;

import com.abnambro.customer.account.manager.dao.CustomerRepository;
import com.abnambro.customer.account.manager.exception.user.UserNameAlreadyExistsException;
import com.abnambro.customer.account.manager.model.entity.Customer;
import com.abnambro.customer.account.manager.model.request.RegistrationRequest;
import com.abnambro.customer.account.manager.model.response.RegistrationResponse;
import com.abnambro.customer.account.manager.service.validator.UserNameValidator;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class RegistrationService {
    public static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String NUMBER = "0123456789";
    @Autowired
    UserNameValidator userNameValidator;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PasswordEncoder encoder;

    public RegistrationResponse register(RegistrationRequest request) {
        if(userNameValidator.validate(request.getUserName())){
            Iban iban = generateIban();
            String defaultPassword = generatePassword();
            customerRepository.save(Customer.builder().userName(request.getUserName())
                    .address(request.getAddress())
                    .iban(iban.toString())
                    .name(request.getName())
                    .idDocNumber(request.getIdDocNumber())
                            .dateOfBirth(request.getDateOfBirth())
                            .password(encoder.encode(defaultPassword))
                    .build());
            return RegistrationResponse.builder().userName(request.getUserName()).password(defaultPassword).build();
        }else{
            throw new UserNameAlreadyExistsException("This username is already in use, please try with another one.");
        }
    }

    private String generatePassword() {
        String specialChar = "!@#%";
        String combination = UPPER + UPPER.toLowerCase() + NUMBER + specialChar;
        int len = 6;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(combination.charAt(
                    ThreadLocalRandom.current().nextInt(
                            combination.length()
                    )
            ));
        }
        return sb.toString();

    }

    private Iban generateIban() {
        return Iban.random(CountryCode.NL);
    }
}
