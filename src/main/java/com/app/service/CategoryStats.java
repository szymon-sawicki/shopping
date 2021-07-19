package com.app.service;

import com.app.domain.product.Product;
import lombok.*;

import java.math.BigDecimal;

@Builder
@RequiredArgsConstructor
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
