package com.abnambro.customer.account.manager.service;

import com.abnambro.customer.account.manager.base.BaseServiceTest;
import com.abnambro.customer.account.manager.dao.CustomerRepository;
import com.abnambro.customer.account.manager.model.entity.Customer;
import com.abnambro.customer.account.manager.model.request.RegistrationRequest;
import com.abnambro.customer.account.manager.model.response.RegistrationResponse;
import com.abnambro.customer.account.manager.service.validator.UserNameValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;
import util.RandomUtil;

public class RegistrationServiceTest extends BaseServiceTest {
    @InjectMocks
    private RegistrationService registrationService;

    @Mock
    UserNameValidator userNameValidator;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    PasswordEncoder encoder;

    @Test
    void givenRegisterRequest_WhenReturnSuccess() {

        // Given
        RegistrationRequest request = RegistrationRequest.builder()
                .name("test_fullname")
                .userName("test_1").address("test_address").dateOfBirth("14-11-1988").idDocNumber("323232")
                .build();

        Customer userEntity = Customer.builder().build().builder()
                .name(request.getName())
                .userName(request.getUserName())
                .password(encoder.encode(RandomUtil.generateRandomString()))
                .build();

        // When
        Mockito.when(userNameValidator.validate(request.getUserName())).thenReturn(true);
        Mockito.when(customerRepository.existsByUserName(request.getUserName())).thenReturn(false);
        Mockito.when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(userEntity);

        // Then
        RegistrationResponse register = registrationService.register(request);
        // Verify
        Mockito.verify(customerRepository).save(Mockito.any(Customer.class));
    }

    @Test
    void givenSignUpRequest_WhenUserNameAlreadyExists_ReturnException() {
        // Given
        RegistrationRequest request = RegistrationRequest.builder()
                .name("test_fullname")
                .userName("test_1").address("test_address").dateOfBirth("14-11-1988").idDocNumber("323232")
                .build();

        // When
        Mockito.when(customerRepository.existsByUserName(request.getUserName())).thenReturn(true);
        Mockito.when(userNameValidator.validate(request.getUserName())).thenReturn(false);

        // Then
        Assertions.assertThrows(Exception.class, () -> registrationService.register(request));

        // Verify
        Mockito.verify(customerRepository, Mockito.never()).save(Mockito.any(Customer.class));

    }

}