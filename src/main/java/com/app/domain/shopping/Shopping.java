package com.app.domain.shopping;

import com.app.domain.customer.Customer;
import com.app.domain.product.Product;
import com.app.domain.shopping.exception.ShoppingException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.util.HashMap;
import java.util.List;
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
    public Shopping(List<String> listOfFiles) {

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


    }

    private boolean checkFile(String filename) {
        File file = new File(filename);
        return file.exists();
    }

}
