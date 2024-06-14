package com.abnambro.customer.account.manager.service.validator;

public interface DataValidator<T> {
    boolean validate(T data);
}
