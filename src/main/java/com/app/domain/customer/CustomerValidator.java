package com.app.domain.customer;

import com.app.domain.config.validator.Validator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * implementation of Validator interface used to validate Customer objects
 *
 * @see com.app.domain.config.validator.Validator
 */

public class CustomerValidator implements Validator<Customer> {

    @Override
    public Map<String, String> validate(Customer customer) {

        var errors = new HashMap<String, String>();

        if (Objects.isNull(customer)) {
            errors.put("customer", "is null");
            return errors;
        }

        var firstName = customer.firstName;

        if (Objects.isNull(firstName)) {

            errors.put("first name", "is null");

        } else {



            if (firstName.length() < 3) {
                errors.put("first name", "is too short");
            }
            if (firstName.replaceAll("[^0-9*&^%$#@!?><+=]+", "").length() > 0) {
                errors.put("first name", "have wrong format");
            }

        }

        var lastName = customer.lastName;
        if (Objects.isNull(lastName)) {
            errors.put("last name", "is null");
        } else {

            if (lastName.length() < 3) {
                errors.put("last name", "is too short");
            }

            if (lastName.replaceAll("[^0-9*&^%$#@!?><+=]+", "").length() > 0) {
                errors.put("last name", "have wrong format");
            }

        }


        var age = customer.age;
        if (age < 0) {
            errors.put("age", "must be positive");
        }

        var cash = customer.cash;
        if (cash == null) {
            errors.put("cash", "is null");
        }

        if (cash.compareTo(BigDecimal.ZERO) < 0) {
            errors.put("cash", "must be positive");
        }

        return errors;

    }


}
