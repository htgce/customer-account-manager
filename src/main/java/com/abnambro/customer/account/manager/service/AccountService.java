package com.abnambro.customer.account.manager.service;

import com.abnambro.customer.account.manager.dao.CustomerRepository;
import com.abnambro.customer.account.manager.model.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class AccountService {
    private final CustomerRepository customerRepository;

    @Autowired
    public AccountService(final CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public Customer getOverview() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Customer user = customerRepository.findByUserName(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " +authentication.getName()));
        return user;
    }
}
