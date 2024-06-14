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
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.context.SecurityContext;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountServiceTest extends BaseServiceTest {
    @InjectMocks
    private AccountService accountService;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    SecurityContextHolder securityContextHolder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtils jwtUtils;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private CustomUserDetails customUserDetails;

   @Test
    void givenOverviewRequest_WhenReturnSuccess() {

        // Given
        Customer mockCustomer = Customer.builder()
                .name("Test User")
                .userName("testuser")
                .password("hashedPassword")
                .build();

        Authentication mockAuthentication = new UsernamePasswordAuthenticationToken(
               mockCustomer.getUserName(),mockCustomer.getPassword());
       SecurityContext securityContext = mock(SecurityContext.class);
       when(securityContext.getAuthentication()).thenReturn(mockAuthentication);
       SecurityContextHolder.setContext(securityContext);

        when(jwtUtils.generateJwtToken(mockAuthentication)).thenReturn("mockedToken");
        when(customerRepository.findByUserName(Mockito.any())).thenReturn(Optional.of(mockCustomer));

        // Then
        Customer response = accountService.getOverview();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(mockCustomer.getUserName(), response.getUserName());

        // Verify
        Mockito.verify(customerRepository).findByUserName(mockCustomer.getUserName());
    }

}
