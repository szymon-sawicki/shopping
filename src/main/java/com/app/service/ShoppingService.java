package com.app.service;


import com.app.domain.customer.Customer;
import com.app.domain.product.Product;
import com.app.domain.shopping.converter.ShoppingConverter;
import com.app.domain.shopping.exception.ShoppingException;
import com.app.service.exception.ShoppingServiceException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.function.ToDoubleBiFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static com.app.domain.shopping.ShoppingUtils.toCustomer;
import static com.app.domain.shopping.ShoppingUtils.toProducts;

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
    Map<Customer, Map<Product, Long>> shoppingMap = new HashMap<Customer, Map<Product, Long>>();

    /**
     * constructor that takes array of filenames, check them and assign to class field
     *
     * @param listOfFiles array of filenames
     */
    public ShoppingService(List<String> listOfFiles) {

        if (listOfFiles == null) {
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

        var tempMap = new HashMap<Customer, List<Product>>();

        filenames.forEach(filename -> {

            var shopping = new ShoppingConverter(filename).fromJson().orElseThrow(() -> new ShoppingServiceException("cannot read file " + filename));
            var customer = toCustomer.apply(shopping);
            var products = toProducts.apply(shopping);




            if (tempMap.containsKey(customer)) {
                var tempList = tempMap.get(customer);
                tempList.addAll(products);
                tempMap.put(customer, tempList);
            } else {
                tempMap.computeIfAbsent(customer, k -> new ArrayList<>()).addAll(products);
            }

            shoppingMap = tempMap.entrySet()
                    .stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, e -> toMapWithStatistics(e.getValue())));



/*
            var lOfProducts = shoppingMap.put(customer, toProducts.apply(shopping)
                    .stream()
                    .collect(
                            Collectors.groupingBy(Function.identity(), Collectors.counting())));

*/


        });

    }

    private Map<Product, Long> toMapWithStatistics(List<Product> listOfProducts) {

        return listOfProducts.stream()
                .collect(
                        Collectors.groupingBy(Function.identity(), Collectors.counting()));

    }

    /**
     * @return hash map with shopping activities
     */

    public Map<Customer, Map<Product, Long>> getShoppingMap() {
        return shoppingMap;
    }

    public Customer customerWithMostExpensiveShopping() {

    return null;

    }

    /**
     *
     * @return sum of all products prices
     */

    public BigDecimal billForShopping() {
        TODO
    }

    /**
     *
     * @return sum of all prices for products from given category
     */

    public BigDecimal billForShoppingFromGivenCategory() {
        TODO
    }

    private boolean checkFile(String filename) {
        File file = new File(filename);
        return file.exists();
    }

}
