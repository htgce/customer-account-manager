package com.abnambro.customer.account.manager.web;

import com.abnambro.customer.account.manager.base.BaseControllerTest;
import com.abnambro.customer.account.manager.model.entity.Customer;
import com.abnambro.customer.account.manager.security.CustomUserDetails;
import com.abnambro.customer.account.manager.service.AccountService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class AccountControllerTest extends BaseControllerTest {

    @MockBean
    AccountService accountService;

    @Test
    void givenOverviewRequestAndAccessToken_WhenReturnCustomerOvereviewSuccess() throws Exception {
        // Given
        Customer mockCustomer = Customer.builder()
                .name("Test User")
                .userName("testuser")
                .password("hashedPassword")
                .build();

        CustomUserDetails userDetails = new CustomUserDetails(mockUserEntity);

        String accessToken = jwtUtils.generateJwtToken(userDetails);

        String mockBearerToken = "Bearer " + accessToken;

        // When
        Mockito.when(accountService.getOverview()).thenReturn(mockCustomer);
        Mockito.when(customUserDetailsService.loadUserByUsername("testuser")).thenReturn(userDetails);

        // Then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/account/overview")
                        .header(HttpHeaders.AUTHORIZATION, mockBearerToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

        // Verify
        Mockito.verify(accountService).getOverview();
        Mockito.verify(customUserDetailsService).loadUserByUsername("testuser");
    }

}
