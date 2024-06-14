package com.abnambro.customer.account.manager.service;

import com.abnambro.customer.account.manager.base.BaseServiceTest;
import com.abnambro.customer.account.manager.dao.CustomerRepository;
import com.abnambro.customer.account.manager.model.entity.Customer;
import com.abnambro.customer.account.manager.model.request.LogonRequest;
import com.abnambro.customer.account.manager.model.response.LogonResponse;
import com.abnambro.customer.account.manager.security.CustomUserDetails;
import com.abnambro.customer.account.manager.security.jwt.JwtUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public class LogonServiceTest extends BaseServiceTest {

    @InjectMocks
    private LogonService logonService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtils jwtUtils;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private CustomUserDetails customUserDetails;

    @Test
    void givenLoginRequest_WhenReturnSuccess() {

        // Given
        LogonRequest request = LogonRequest.builder()
                .userName("testuser")
                .password("hashedPassword")
                .build();

        Customer mockCustomer = Customer.builder()
                .name("Test User")
                .userName("testuser")
                .password("hashedPassword")
                .build();

        Authentication mockAuthentication = new UsernamePasswordAuthenticationToken(
                request.getUserName(), request.getPassword());
        // When
        Mockito.when(authenticationManager.authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(mockAuthentication);
        Mockito.when(jwtUtils.generateJwtToken(mockAuthentication)).thenReturn("mockedToken");
        Mockito.when(customerRepository.findByUserName(request.getUserName())).thenReturn(Optional.of(mockCustomer));

        // Then
        LogonResponse jwtResponse = logonService.logon(request);

        Assertions.assertNotNull(jwtResponse);
        Assertions.assertEquals(request.getUserName(), jwtResponse.getUserName());
        Assertions.assertEquals("mockedToken", jwtResponse.getToken());

        // Verify
        Mockito.verify(authenticationManager).authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class));
        Mockito.verify(jwtUtils).generateJwtToken(mockAuthentication);
        Mockito.verify(customerRepository).findByUserName(request.getUserName());
    }

    @Test
    void givenLoginRequest_WhenAdminRole_ReturnRuntimeException() {

        // Given
        LogonRequest request = LogonRequest.builder()
                .userName("testuser")
                .password("hashedPassword")
                .build();

        // When
        Mockito.when(authenticationManager.authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(null);

        // Then
        Assertions.assertThrows(RuntimeException.class, () -> logonService.logon(request));

        // Verify
        Mockito.verify(authenticationManager).authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class));

    }
}