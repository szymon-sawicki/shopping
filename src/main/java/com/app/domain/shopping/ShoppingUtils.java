package com.app.domain.shopping;

import com.app.domain.customer.Customer;
import com.app.domain.product.Product;

import java.util.List;
import java.util.function.Function;

/**
 * Utility interface with some implementations of functional interface used to achieve encapsulation of shopping objects.
 * @author Szymon Sawicki
 */

public interface ShoppingUtils {

    Function<Shopping, Customer> toCustomer = shopping -> shopping.customer;
    Function<Shopping, List<Product>> toProducts = shopping -> shopping.products;

}
