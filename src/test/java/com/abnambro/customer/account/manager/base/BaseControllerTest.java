package com.abnambro.customer.account.manager.base;

import com.abnambro.customer.account.manager.builder.CustomerBuilder;
import com.abnambro.customer.account.manager.config.SecurityConfig;
import com.abnambro.customer.account.manager.model.entity.Customer;
import com.abnambro.customer.account.manager.security.CustomUserDetails;
import com.abnambro.customer.account.manager.security.CustomUserDetailsService;
import com.abnambro.customer.account.manager.security.jwt.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@Import({SecurityConfig.class})
public abstract class BaseControllerTest extends AbstractTestContainerConfiguration {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected CustomUserDetailsService customUserDetailsService;

    @Autowired
    protected JwtUtils jwtUtils;

    protected Customer mockUserEntity;

    protected String mockUserToken;

    @Autowired
    SecurityConfig securityConfig;

    @BeforeEach
    protected void initializeAuth() {
        this.mockUserEntity =Customer.builder()
                .name("Test User")
                .userName("testuser")
                .password("hashedPassword")
                .build();

        final CustomUserDetails mockUserDetails = new CustomUserDetails(mockUserEntity);

        this.mockUserToken = generateMockToken(mockUserDetails);

        Mockito.when(customUserDetailsService.loadUserByUsername(mockUserEntity.getUserName())).thenReturn(mockUserDetails);
    }

    private String generateMockToken(CustomUserDetails details) {
        return "Bearer ".concat(jwtUtils.generateJwtToken(details));
    }
}
