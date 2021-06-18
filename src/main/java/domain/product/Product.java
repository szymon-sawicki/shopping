package domain.product;

import domain.product.type.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@Builder

public class Product {
    String name;
    Category category;
    BigDecimal price;

}
