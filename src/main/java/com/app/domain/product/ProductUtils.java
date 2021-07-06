package com.app.domain.product;

import com.app.domain.product.type.Category;

import java.math.BigDecimal;
import java.util.function.Function;

/**
 * Utility interface with some implementations of functional interfaces used to achieve encapsulation of product objects.
 * @author Szymon Sawicki
 */

public interface ProductUtils {

    Function<Product, BigDecimal> toPrice = product -> product.price;
    Function<Product, Category> toCategory = product -> product.category;

}
