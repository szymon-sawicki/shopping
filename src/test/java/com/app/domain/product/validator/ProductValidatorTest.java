package com.app.domain.product.validator;

import com.app.domain.config.validator.Validator;
import com.app.domain.config.validator.ValidatorException;
import com.app.domain.product.Product;
import com.app.domain.product.ProductValidator;
import com.app.domain.product.type.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

public class ProductValidatorTest {

    @Test
    @DisplayName("when product is null")
    public void test1() {
        var productValidator = new ProductValidator();
        assertThatThrownBy(() -> Validator.validate(productValidator, null))
                .isInstanceOf(ValidatorException.class)
                .hasMessageContaining("[VALIDATION ERRORS]:")
                .hasMessageContaining("product: is null");
    }

    @Test
    @DisplayName("when product name is too short")
    public void test2() {
        var productValidator = new ProductValidator();
        var product = Product.builder()
                .name("aa")
                .category(Category.BOOK)
                .price(BigDecimal.TEN)
                .build();

        assertThatThrownBy(() -> Validator.validate(productValidator, product))
                .isInstanceOf(ValidatorException.class)
                .hasMessageContaining("[VALIDATION ERRORS]:")
                .hasMessageContaining("name: is too short - 2");

    }

    @Test
    @DisplayName("when price is negative")
    public void test3() {
        var productValidator = new ProductValidator();
        var product = Product.builder()
                .name("aa")
                .category(Category.BOOK)
                .price(new BigDecimal("-10"))
                .build();

        assertThatThrownBy(() -> Validator.validate(productValidator, product))
                .isInstanceOf(ValidatorException.class)
                .hasMessageContaining("[VALIDATION ERRORS]:")
                .hasMessageContaining("price: must have positive value");

    }

    @Test
    @DisplayName("when price is null")
    public void test4() {
        var productValidator = new ProductValidator();
        var product = Product.builder()
                .name("aa")
                .category(Category.BOOK)
                .price(null)
                .build();

        assertThatThrownBy(() -> Validator.validate(productValidator, product))
                .isInstanceOf(ValidatorException.class)
                .hasMessageContaining("[VALIDATION ERRORS]:")
                .hasMessageContaining("price: is null");

    }



    @Test
    @DisplayName("when category is null")
    public void test5() {
        var productValidator = new ProductValidator();
        var product = Product.builder()
                .name("aa")
                .category(null)
                .price(new BigDecimal("-10"))
                .build();

        assertThatThrownBy(() -> Validator.validate(productValidator, product))
                .isInstanceOf(ValidatorException.class)
                .hasMessageContaining("[VALIDATION ERRORS]:")
                .hasMessageContaining("category: is null");

    }


}
