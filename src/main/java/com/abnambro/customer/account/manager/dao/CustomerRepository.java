package com.abnambro.customer.account.manager.dao;

import com.abnambro.customer.account.manager.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByUserName(String userName);
    Boolean existsByUserName(String userName);
}
