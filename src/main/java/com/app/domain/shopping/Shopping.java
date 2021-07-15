package com.app.domain.shopping;

import com.app.domain.customer.Customer;
import com.app.domain.product.Product;
import com.app.domain.product.type.Category;
import com.app.domain.shopping.exception.ShoppingException;
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
 * Class represents cutomer and their shopping
 * @Author: Szymon Sawicki
 */

public class Shopping {


    Customer customer;

    /**
     *  bought products
     */

    List<Product> products;


}
