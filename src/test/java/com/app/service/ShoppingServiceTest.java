package com.app.service;

import com.app.domain.customer.Customer;
import com.app.domain.customer.CustomerUtils;
import com.app.domain.product.Product;
import com.app.domain.product.ProductUtils;
import com.app.domain.product.type.Category;
import com.app.extension.ShoppingServiceExtension;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(ShoppingServiceExtension.class)
@RequiredArgsConstructor

public class ShoppingServiceTest {

    private final ShoppingService shoppingService;

    @Test
    @DisplayName("when all customers are loaded to map properly")
    public void test1() {

        var expectedLastNames = List.of("Comanch", "Ripponi", "Carbonara", "McDonald");

        var shoppingMap = shoppingService.shoppingMap;

        var givenNames = shoppingMap.keySet().stream()
                .map(CustomerUtils.toLastName::apply);

        assertThat(givenNames)
                .hasSize(4)
                .containsAll(expectedLastNames);
    }

    @Test
    @DisplayName("when all products bought by the customer are loaded to map properly")
    public void test2() {

        var expectedProductsCount = 5;

        var expectedNames = List.of("HARRY POTTER","WALKMAN","HI-FI","BALL","LORD OF THE RINGS");

        var customer = Customer.builder()
                .firstName("Macaroni")
                .lastName("Carbonara")
                .age(17)
                .cash(new BigDecimal("90"))
                .build();

        var customerProducts = shoppingService.getShoppingMap()
                .get(customer)
                .keySet()
                .stream()
                .map(ProductUtils.toName::apply);

        assertThat(customerProducts)
                .hasSize(5)
                .containsAll(expectedNames);

    }

    @Test
    @DisplayName("when products are counted properly")
    public void test3() {


        var customer = Customer.builder()
                .firstName("Macaroni")
                .lastName("Carbonara")
                .age(17)
                .cash(new BigDecimal("90"))
                .build();

        var product = Product.builder()
                .name("HARRY POTTER")
                .category(Category.BOOK)
                .price(new BigDecimal("20"))
                .build();

        var productsMap = shoppingService.getShoppingMap().get(customer);

        assertThat(productsMap.get(product))
                .isEqualTo(3);

    }

}
