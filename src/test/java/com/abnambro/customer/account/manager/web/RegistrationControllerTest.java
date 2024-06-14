package com.abnambro.customer.account.manager.web;

import com.abnambro.customer.account.manager.base.BaseControllerTest;
import com.abnambro.customer.account.manager.config.SecurityConfig;
import com.abnambro.customer.account.manager.model.request.RegistrationRequest;
import com.abnambro.customer.account.manager.model.response.RegistrationResponse;
import com.abnambro.customer.account.manager.service.RegistrationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@Import(SecurityConfig.class)
public class RegistrationControllerTest extends BaseControllerTest {

    @MockBean
    RegistrationService registrationService;


    @Test
    void givenRegisterRequest_WhenReturnSuccess() throws Exception {

        // Given
        RegistrationRequest request = RegistrationRequest.builder()
                .name("test_fullname")
                .userName("test_1").address("test_address").dateOfBirth("14-11-1988").idDocNumber("323232")
                .build();
        RegistrationResponse registrationResponse = RegistrationResponse.builder().build();
        // When
        Mockito.when(registrationService.register(request)).thenReturn(registrationResponse);

        // Then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/register").
                        contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        // Verify
        Mockito.verify(registrationService).register(request);

    }
}