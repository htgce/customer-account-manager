package com.abnambro.customer.account.manager.builder;

import com.abnambro.customer.account.manager.model.entity.Customer;
import util.RandomUtil;

import java.util.Collections;

public class CustomerBuilder extends BaseBuilder<Customer> {

    public CustomerBuilder() {
        super(Customer.class);
    }

    public CustomerBuilder customer() {
        return this
                .withId("1")
                .withFullName(RandomUtil.generateRandomString())
                .withUsername(RandomUtil.generateRandomString());}

    public CustomerBuilder withId(String id) {
        data.setId(Long.valueOf(id));
        return this;
    }

    public CustomerBuilder withFullName(String fullName) {
        data.setName(fullName);
        return this;
    }

    public CustomerBuilder withUsername(String username) {
        data.setUserName(username);
        return this;
    }

}
