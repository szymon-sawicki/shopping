package domain.product;

import domain.product.type.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.math.BigDecimal;

@AllArgsConstructor
@Builder

/**
 * class representing product bought by customer
 * @Author: Szymon Sawicki
 */

public class Product {

    String name;
    Category category;
    BigDecimal price;

}
