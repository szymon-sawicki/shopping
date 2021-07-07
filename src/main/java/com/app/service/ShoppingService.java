package com.app.service;


import com.app.domain.customer.Customer;
import com.app.domain.customer.CustomerUtils;
import com.app.domain.product.Product;
import com.app.domain.shopping.ShoppingUtils;
import com.app.domain.shopping.converter.ShoppingConverter;
import com.app.domain.shopping.exception.ShoppingException;
import com.app.service.exception.ShoppingServiceException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static com.app.domain.shopping.ShoppingUtils.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Builder

/**
 * class to manage shopping activities of customer. It contains list of filenames from which data will be loaded and
 * map with all customers as keys and another map as value. Map contains key - Product and as value amount of bought products.
 * @Author: Szymon Sawicki
 */

public class ShoppingService {

    /**
     * list of filenames from which shopping data will be loaded
     */
    List<String> filenames;

    /**
     * map with customers and their shopping lists
     * customer is key, value is another map with product as key and amount of that product as value
     */
    HashMap<Customer, HashMap<Product, Integer>> shoppingMap;

    /**
     * constructor that takes array of filenames, check them and assign to class field
     *
     * @param listOfFiles array of filenames
     */
    public ShoppingService(List<String> listOfFiles) {

        if(listOfFiles == null) {
            throw new ShoppingException("list of files cannot be null");
        }

        listOfFiles.forEach(filename -> {
            if (!checkFile(filename)) {
                throw new ShoppingException("cannot read file");
            }
        });

        this.filenames = listOfFiles;
        init();
    }

    /**
     * initialization method that reads all files from filenames field and loads it to shoppingMap field
     */

    private void init() {

        if (Objects.isNull(filenames)) {
            throw new ShoppingException("filenames cannot be null");
        }

        filenames.forEach(item -> {
           var shopping = new ShoppingConverter(item).fromJson().orElseThrow();


          // shoppingMap.put(toCustomer.apply(shopping),toProducts.apply(shopping).stream()


        });


    }

    private boolean checkFile(String filename) {
        File file = new File(filename);
        return file.exists();
    }

}
