package com.app.service.data;

import com.app.domain.product.Product;
import lombok.*;

import java.math.BigDecimal;
import java.util.Map;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString

/**
 * value object that represents statistics of category
 * @Author Szymon Sawicki
 */

public class CategoryStats {

    Product mostExpensive;
    Product cheapest;
    BigDecimal average;





}
