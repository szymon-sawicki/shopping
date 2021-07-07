package com.app.domain.shopping;

import com.app.domain.customer.Customer;
import com.app.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.util.List;

@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@EqualsAndHashCode

/**
 *
 *
 * @Author: Szymon Sawicki
 */

public class Shopping {

    Customer customer;
    List<Product> products;


}
