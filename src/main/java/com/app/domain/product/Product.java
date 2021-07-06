package com.app.domain.product;

import com.app.domain.product.type.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.math.BigDecimal;

@AllArgsConstructor
@Builder

/**
 * class representing product that can be buy by consumer
 * @Author: Szymon Sawicki
 */

public class Product {

    /**
     * unique name of product
     */
    String name;

    /**
     * product's category
     * @see Category
     */
    Category category;

    /**
     * price of product
     */
    BigDecimal price;

}
