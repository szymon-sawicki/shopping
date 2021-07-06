package com.app.domain.customer;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.app.domain.customer.CustomerUtils.*;
import static org.assertj.core.api.Assertions.*;

public class CustomerUtilsTest {

    @Test
    @DisplayName("when age is 30")
    public void test1() {

        var customer = Customer.builder()
                .age(30)
                .build();

        assertThat(toAge.apply(customer))
                .isEqualTo(30);


    }

    @Test
    @DisplayName("when cash is 100")
    public void test2() {

        var customer = Customer.builder()
                .cash(new BigDecimal("100"))
                .build();

        assertThat(toCash.apply(customer))
                .isEqualTo(new BigDecimal("100"));


    }

}
