package com.app.domain.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString

/**
 * Domain object representing customer, contains few simple properties
 * @Author Szymon Sawicki
 */

public class Customer {

    /**
     * first name of customer
     */
    String firstName;

    /**
     * last name of customer
     */
    String lastName;

    /**
     * customer's age
     */
    int age;

    /**
     * amount of customer's cash
     */
    BigDecimal cash;

}
