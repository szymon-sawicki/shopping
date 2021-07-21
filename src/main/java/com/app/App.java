package com.app;


import com.app.domain.product.type.Category;
import com.app.service.ShoppingService;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class App {

    public static void main(String[] args) {

        AtomicInteger atomicInteger = new AtomicInteger(1);
        var path = "src/main/resources/shoppings/";

        // list of filenames is generated and injected into constructor - ShoppingService

        var filenames = Stream.generate(() -> path + atomicInteger.getAndIncrement() + ".json")
                .limit(8)
                .toList();

        var shoppingService = new ShoppingService(filenames);

        System.out.println(shoppingService.getMapWithCategoryStatistics());

    }

}
