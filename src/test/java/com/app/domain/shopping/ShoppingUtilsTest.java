package com.app.domain.shopping;

import com.app.domain.customer.Customer;
import com.app.domain.product.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ShoppingUtilsTest {

    @Test
    @DisplayName("when cutomer is given")
    public void test1() {

        var customer = Customer.builder()
                .age(20)
                .build();

        var expectedCustomer = Customer.builder()
                .age(20)
                .build();

        var shopping = Shopping.builder()
                .customer(customer)
                .build();

        Assertions.assertThat(ShoppingUtils.toCustomer.apply(shopping))
                .isEqualTo(expectedCustomer);

    }

    @Test
    @DisplayName("when products are given")
    public void test2() {

        var productsList = List.of(Product.builder().name("aaa").build());
        var expectedProductsList = List.of(Product.builder().name("aaa").build());

        var shopping = Shopping.builder()
                .products(productsList)
                .build();

        Assertions.assertThat(ShoppingUtils.toProducts.apply(shopping))
                .isEqualTo(expectedProductsList);

    }

}
