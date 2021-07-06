package com.app.domain.customer.validator;

import com.app.domain.config.validator.Validator;
import com.app.domain.config.validator.ValidatorException;
import com.app.domain.customer.Customer;
import com.app.domain.customer.CustomerValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

public class CustomerValidatorTest {

    @Test
    @DisplayName("when customer is null")
    public void test1() {

        var customerValidator = new CustomerValidator();

        assertThatThrownBy(() -> Validator.validate(customerValidator,null))
                .isInstanceOf(ValidatorException.class)
                .hasMessageContaining("[VALIDATION ERRORS]:")
                .hasMessageContaining("customer: is null");

    }

    @Test
    @DisplayName("when first name is too short")
    public void test2() {

        var customerValidator = new CustomerValidator();

        var customer = Customer.builder()
                .firstName("aa")
                .lastName("kowalski")
                .age(20)
                .cash(BigDecimal.TEN)
                .build();

        assertThatThrownBy(() -> Validator.validate(customerValidator,customer))
                .isInstanceOf(ValidatorException.class)
                .hasMessageStartingWith("[VALIDATION ERRORS]:")
                .hasMessageContaining("first name: is too short");

    }

    @Test
    @DisplayName("when last name contains number")
    public void test3() {

        var customerValidator = new CustomerValidator();

        var customer = Customer.builder()
                .firstName("Janek")
                .lastName("kowal9ski")
                .age(20)
                .cash(BigDecimal.TEN)
                .build();

        assertThatThrownBy(() -> Validator.validate(customerValidator,customer))
                .isInstanceOf(ValidatorException.class)
                .hasMessageStartingWith("[VALIDATION ERRORS]:")
                .hasMessageContaining("last name: have wrong format");

    }

    @Test
    @DisplayName("when last name is null")
    public void test4() {

        var customerValidator = new CustomerValidator();

        var customer = Customer.builder()
                .firstName("Janek")
                .lastName(null)
                .age(20)
                .cash(BigDecimal.TEN)
                .build();

        assertThatThrownBy(() -> Validator.validate(customerValidator,customer))
                .isInstanceOf(ValidatorException.class)
                .hasMessageStartingWith("[VALIDATION ERRORS]:")
                .hasMessageContaining("last name: is null");

    }

    @Test
    @DisplayName("when last name is null")
    public void test5() {

        var customerValidator = new CustomerValidator();

        var customer = Customer.builder()
                .firstName(null)
                .lastName("Schmidt")
                .age(20)
                .cash(BigDecimal.TEN)
                .build();

        assertThatThrownBy(() -> Validator.validate(customerValidator,customer))
                .isInstanceOf(ValidatorException.class)
                .hasMessageStartingWith("[VALIDATION ERRORS]:")
                .hasMessageContaining("first name: is null");

    }

    @Test
    @DisplayName("when cash is  negative")

    public void test6() {

        var customerValidator = new CustomerValidator();

        var customer = Customer.builder()
                .firstName("Andreas")
                .lastName("Schmidt")
                .age(20)
                .cash(new BigDecimal("-30"))
                .build();

        assertThatThrownBy(() -> Validator.validate(customerValidator,customer))
                .isInstanceOf(ValidatorException.class)
                .hasMessageStartingWith("[VALIDATION ERRORS]:")
                .hasMessageContaining("cash: must be positive");

    }


    @Test
    @DisplayName("when age is negative")

    public void test7() {

        var customerValidator = new CustomerValidator();

        var customer = Customer.builder()
                .firstName("Andreas")
                .lastName("Schmidt")
                .age(-10)
                .cash(BigDecimal.TEN)
                .build();

        assertThatThrownBy(() -> Validator.validate(customerValidator,customer))
                .isInstanceOf(ValidatorException.class)
                .hasMessageStartingWith("[VALIDATION ERRORS]:")
                .hasMessageContaining("age: must be positive");

    }

    @Test
    @DisplayName("when validator has no errors")

    public void test8() {

        var customerValidator = new CustomerValidator();

        var customer = Customer.builder()
                .firstName("Andreas")
                .lastName("Schmidt")
                .age(30)
                .cash(BigDecimal.TEN)
                .build();

        Assertions.assertDoesNotThrow(() -> Validator.validate(customerValidator,customer));
    }






}
