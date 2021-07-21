package com.app.service;

import com.app.domain.customer.Customer;
import com.app.domain.product.Product;
import com.app.domain.product.ProductUtils;
import com.app.domain.product.type.Category;
import com.app.extension.ShoppingServiceExtension;
import com.app.service.data.CategoryStats;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import static com.app.domain.customer.CustomerUtils.toLastName;
import static org.assertj.core.api.Assertions.assertThat;

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
                .map(toLastName::apply);

        assertThat(givenNames)
                .hasSize(4)
                .containsAll(expectedLastNames);
    }

    @Test
    @DisplayName("when all products bought by the customer are loaded to map properly")
    public void test2() {


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

    @Test
    @DisplayName("when customer with greatest bill is returned")
    public void test4() {

        var expectedCustomerLastName = "Carbonara";

        var customerLastName = toLastName.apply((shoppingService.greatestBill()));

        assertThat(customerLastName)
                .isEqualTo(expectedCustomerLastName);

    }

    @Test
    @DisplayName("when customer with greatest bill in category electronic is returned")
    public void test5() {

        var expectedLastname = "Comanch";

        var lastName = toLastName.apply(shoppingService.greatestBillFromCategory(Category.ELECTRONIC));

        assertThat(lastName)
                .isEqualTo(expectedLastname);

    }

/*
TODO
    @Test
    @DisplayName("when map with age of customers is returned")
    public void test6() {

        var expectedSetOfAges = Set.of(21,34,17,67);

        var mapOfAges = shoppingService.ageStats();
        var setOfAges = mapOfAges.keySet();

        assertThat(expectedSetOfAges)
                .containsAll(setOfAges);

        assertThat(mapOfAges)
                .containsEntry(21,Category.ELECTRONIC);

    }
*/

    @Test
    @DisplayName("when statistics of category SPORT are returned")
    public void test8() {

        var expectedMin = new BigDecimal("17");
        var expectedMax= new BigDecimal("45");
        var expectedAvg = new BigDecimal("31");

        var sportStats = shoppingService.getCategoryStats(Category.SPORT);

        Assertions.assertAll(
                () -> assertThat(ProductUtils.toPrice.apply(sportStats.getCheapest())).isEqualTo(expectedMin),
                () -> assertThat(ProductUtils.toPrice.apply(sportStats.getMostExpensive())).isEqualTo(expectedMax),
                () -> assertThat(sportStats.getAverage()).isEqualTo(expectedAvg));
    }

    @Test
    @DisplayName("when map with customers debts is returned")
    public void test7() {

        var mapWithmapWithCash = shoppingService.customersCashAfterShopping();

        var expectedCustomer = Customer.builder()
                .firstName("Merry")
                .lastName("McDonald")
                .age(67)
                .cash(new BigDecimal("280"))
                .build();

        var expectedCustomer2 = Customer.builder()
                .firstName("Andrea")
                .lastName("Ripponi")
                .age(34)
                .cash(new BigDecimal("150"))
                .build();

        var expectedCustomer3 = Customer.builder()
                .firstName("Macaroni")
                .lastName("Carbonara")
                .age(17)
                .cash(new BigDecimal("90"))
                .build();

        assertThat(mapWithmapWithCash)
                .hasSize(4)
                .containsEntry(expectedCustomer,new BigDecimal("173"))
                .containsEntry(expectedCustomer2,new BigDecimal("38"))
                .containsEntry(expectedCustomer3,new BigDecimal("-95"));

    }

    @Test
    @DisplayName("when map with statistics of all categories")
    public void test9() {

        var mapWithCategoryStats = shoppingService.getMapWithCategoryStatistics();

        assertThat(mapWithCategoryStats)
                .isNotEmpty()
                .hasSize(3);


    }


}
