package com.abnambro.customer.account.manager.service.validator;

import com.abnambro.customer.account.manager.dao.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserNameValidator implements DataValidator<String>{
    CustomerRepository repository;

    @Autowired
    public UserNameValidator(CustomerRepository repository){
        this.repository = repository;
    }

    @Override
    public boolean validate(String userName) {
        return !repository.existsByUserName(userName);
    }
}
