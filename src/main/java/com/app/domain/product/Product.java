package com.app.domain.product;

import com.app.domain.product.type.Category;
import com.app.domain.shopping.exception.ShoppingException;
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


    public boolean hasCategory(Category category) {
        if(category == null) {
            throw new ShoppingException("category is null");
        }
        return this.category.equals(category);
    }

}
