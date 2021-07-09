package com.app.domain.customer;

import java.math.BigDecimal;
import java.util.function.Function;

/**
 * Utility interface with some implementations of functional interface used to achieve encapsulation of customer  objects.
 * @author Szymon Sawicki
 */

public interface CustomerUtils {

    Function<Customer,Integer> toAge = customer -> customer.age;
    Function<Customer, BigDecimal> toCash = customer -> customer.cash;
    Function<Customer, String> toLastName = customer -> customer.lastName;
}
