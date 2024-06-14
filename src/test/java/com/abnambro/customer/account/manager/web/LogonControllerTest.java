package com.abnambro.customer.account.manager.web;

import com.abnambro.customer.account.manager.base.BaseControllerTest;
import com.abnambro.customer.account.manager.config.SecurityConfig;
import com.abnambro.customer.account.manager.model.request.LogonRequest;
import com.abnambro.customer.account.manager.model.request.RegistrationRequest;
import com.abnambro.customer.account.manager.model.response.LogonResponse;
import com.abnambro.customer.account.manager.model.response.RegistrationResponse;
import com.abnambro.customer.account.manager.service.LogonService;
import com.abnambro.customer.account.manager.service.RegistrationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@Import(SecurityConfig.class)
public class LogonControllerTest extends BaseControllerTest {

    @MockBean
    LogonService logonService;


    @Test
    void givenLogonRequest_WhenReturnSuccess() throws Exception {

        // Given
        LogonRequest request = LogonRequest.builder()
                .userName("testuser")
                .password("hashedPassword")
                .build();
        LogonResponse logonResponse = LogonResponse.builder().build();
        // When
        Mockito.when(logonService.logon(request)).thenReturn(logonResponse);

        // Then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/logon").
                        contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Verify
        Mockito.verify(logonService).logon(request);

    }
}