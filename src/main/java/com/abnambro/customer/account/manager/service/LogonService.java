package com.abnambro.customer.account.manager.service;

import com.abnambro.customer.account.manager.dao.CustomerRepository;
import com.abnambro.customer.account.manager.exception.user.UserNotFoundException;
import com.abnambro.customer.account.manager.model.entity.Customer;
import com.abnambro.customer.account.manager.model.request.LogonRequest;
import com.abnambro.customer.account.manager.model.response.LogonResponse;
import com.abnambro.customer.account.manager.security.CustomUserDetails;
import com.abnambro.customer.account.manager.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LogonService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    public LogonResponse logon(LogonRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtCookie = jwtUtils.generateJwtToken(authentication);
        Customer userEntity = customerRepository.findByUserName(loginRequest.getUserName()).orElseThrow(UserNotFoundException::new);
        return LogonResponse.builder().userName(userEntity.getUserName()).token(jwtCookie).build();
    }
}
