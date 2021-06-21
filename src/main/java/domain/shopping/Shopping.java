package domain.shopping;

import domain.customer.Customer;
import domain.product.Product;
import domain.shopping.exception.ShoppingException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Objects;

@AllArgsConstructor
@RequiredArgsConstructor
@Builder

/**
 * class to manage shopping activities of customer. It contains list of filenames from which data will be loaded and
 * map with all customers as keys and another map as value. Map contains key - Product and as value amount of bought products.
 * @Author: Szymon Sawicki
 */

public class Shopping {

    String[] filenames;
    HashMap<Customer, HashMap<Product, Integer>> shoppingMap;

    public Shopping(String... files) {
        this.filenames = files;
        init();
    }

    private void init() {
        if (Objects.isNull(filenames)) {
            throw new ShoppingException("filenames cannot be null");
        }

        //todo zrobić metodę zczytującą pliki

    }

}
