package com.abnambro.customer.account.manager.security;

import com.abnambro.customer.account.manager.dao.CustomerRepository;
import com.abnambro.customer.account.manager.exception.user.UserNotFoundException;
import com.abnambro.customer.account.manager.model.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service class named {@link CustomUserDetailsService} for loading user-specific data.
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    /**
     * Locates the user based on the username.
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record.
     * @throws UsernameNotFoundException if the user could not be found or the user has no GrantedAuthority.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Customer customer = null;
        try {
            customer = customerRepository.findByUserName(username)
                    .orElseThrow(() -> new UserNotFoundException("User Name " + username + " not found"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return new CustomUserDetails(customer);

    }

}
