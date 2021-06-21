package domain.customer;

import domain.config.validator.Validator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CustomerValidator implements Validator<Customer> {

    @Override
    public Map<String, String> validate(Customer customer) {

        var errors = new HashMap<String, String>();

        if(Objects.isNull(customer)) {

            errors.put("customer", "is null");

        }

        var firstName = customer.firstName;

        if(Objects.isNull(firstName)) {

            errors.put("first name","is null");

        }

        if(firstName.length() < 3 || firstName.replaceAll("[^0-9*&^%$#@!?><+=]+","").length() > 0) {
            errors.put("last name","have wrong format");
        }

        var lastName = customer.lastName;

        if(Objects.isNull(lastName)) {

            errors.put("last name","is null");

        }

        if(lastName.length() < 3 || lastName.replaceAll("[^0-9*&^%$#@!?><+=]+","").length() > 0) {
            errors.put("last name","have wrong format");
        }

        var age = customer.age;
        if(age < 0) {
            errors.put("age","must be positive");
                    }

        var cash = customer.cash;
        if(cash == null) {
            errors.put("cash","is null");
        }

        if(cash.compareTo(BigDecimal.ZERO) < 0) {
            errors.put("cash","must be positiive value");
        }

        return errors;

    }




}
