package com.app.domain.product;

import com.app.domain.product.type.Category;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.app.domain.product.ProductUtils.*;
import static org.assertj.core.api.Assertions.*;

public class ProductUtilsTest {

    @Test
    @DisplayName("when product is from book category")
    public void test1() {

        var product = Product.builder()
                .category(Category.BOOK)
                .build();

        assertThat(toCategory.apply(product))
                .isEqualTo(Category.BOOK);

    }

    @Test
    @DisplayName("when product have price 100")
    public void test2() {

        var product = Product.builder()
                .price(new BigDecimal("100"))
                .build();

        assertThat(toPrice.apply(product))
                .isEqualTo(new BigDecimal("100"));

    }

}
