package com.app.service;


import com.app.domain.customer.Customer;
import com.app.domain.customer.CustomerUtils;
import com.app.domain.product.Product;
import com.app.domain.product.type.Category;
import com.app.domain.shopping.converter.ShoppingConverter;
import com.app.domain.shopping.exception.ShoppingException;
import com.app.service.data.CategoryStats;
import com.app.service.exception.ShoppingServiceException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.app.domain.product.ProductUtils.*;
import static com.app.domain.shopping.ShoppingUtils.toCustomer;
import static com.app.domain.shopping.ShoppingUtils.toProducts;

@AllArgsConstructor
@RequiredArgsConstructor
@Builder

/**
 * class to manage shopping activities of customer. It contains list of filenames from which data will be loaded and
 * map with all customers as keys and another map as value. This map contains key - Product and as value amount of bought products.
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
                tempMap.computeIfAbsent(customer, k -> new ArrayList<>(products));
            }

            shoppingMap = tempMap.entrySet()
                    .stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, e -> getMapWithProductsStatistics(e.getValue())));

        });

    }

    /**
     * @return map with category statistics category as key and CategoryStats as value(most expensive and cheapest products, average price)
     * @see CategoryStats
     */

    public Map<Category, CategoryStats> getMapWithCategoryStatistics() {

        return getAllProducts()
                .stream()
                .map(toCategory::apply)
                .distinct()
                .collect(Collectors.toMap(Function.identity(), this::getCategoryStats));

    }

    /**
     *
     * @param category
     * @return CategoryStats object with statistics of given category
     * @see CategoryStats
     */

    public CategoryStats getCategoryStats(Category category) {
        if (category == null) {
            throw new ShoppingServiceException("category is null");
        }

        var products = getAllProducts().stream().filter(product -> toCategory.apply(product).equals(category)).toList();

        Product cheapest = products.stream()
                .min(Comparator.comparing(product -> toPrice.apply(product)))
                .orElseThrow();

        Product mostExpensive = products.stream()
                .max(Comparator.comparing(product -> toPrice.apply(product)))
                .orElseThrow();

        BigDecimal averagePrice = products.stream()
                .map(product -> toPrice.apply(product))
                .reduce(BigDecimal::add)
                .orElseThrow()
                .divide(new BigDecimal(products.size()));

        return CategoryStats.builder()
                .cheapest(cheapest)
                .mostExpensive(mostExpensive)
                .average(averagePrice)
                .build();


    }


    private Map<Product, Long> getMapWithProductsStatistics(List<Product> listOfProducts) {

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


    /**
     * @return customer with greatest bill
     */

    public Customer greatestBill() {

        return shoppingMap.entrySet()
                .stream()
                .max(Comparator.comparing(entry -> totalShoppingAmount(entry.getValue())))
                .map(Map.Entry::getKey)
                .orElseThrow();

    }


    /**
     * @param category
     * @return customer with greatest bill from given category
     */

    public Customer greatestBillFromCategory(Category category) {
        if (category == null) {
            throw new ShoppingServiceException("category is null");
        }

        return shoppingMap.entrySet()
                .stream()
                .max(Comparator.comparing(entry -> totalShoppingAmountInCategory(entry.getValue(), category)))
                .map(Map.Entry::getKey)
                .orElseThrow();

    }

    /**
     * @return map with customers as keys and their cash after shopping as values
     */

    public Map<Customer, BigDecimal> customersCashAfterShopping() {

        return shoppingMap.entrySet()
                .stream()
                .collect(Collectors.toMap(entry -> entry.getKey(), v -> {
                    return CustomerUtils.toCash.apply(v.getKey()).subtract(
                            v.getValue()
                                    .entrySet()
                                    .stream()
                                    .map(e -> toPrice.apply(e.getKey()).multiply(new BigDecimal(e.getValue())))
                                    .reduce(BigDecimal.ZERO, BigDecimal::add)
                    );
                }));
    }

    private BigDecimal totalShoppingAmount(Map<Product, Long> map) {

        return map.entrySet().stream()
                .map(entry -> toPrice.apply(entry.getKey()).multiply(BigDecimal.valueOf(entry.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }


    private BigDecimal totalShoppingAmountInCategory(Map<Product, Long> map, Category category) {

        return map.entrySet()
                .stream()
                .filter(entry -> toCategory.apply(entry.getKey()).equals(category))
                .map(a -> toPrice.apply(a.getKey()).multiply(new BigDecimal(a.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }

    private List<Product> getAllProducts() {

        return   shoppingMap.values()
                .stream()
                .flatMap(map -> map.keySet().stream())
                .distinct()
                .toList();

    }

    private boolean checkFile(String filename) {
        File file = new File(filename);
        return file.exists();
    }


}
