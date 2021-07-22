package com.app;


import com.app.domain.product.type.Category;
import com.app.infrastructure.Routing;
import com.app.service.ShoppingService;
import spark.Spark;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class App {

    public static void main(String[] args) {


        Spark.initExceptionHandler(e->System.out.println(e.getMessage()));

        Spark.port(8000);

        AtomicInteger atomicInteger = new AtomicInteger(1);
        var path = "src/main/resources/shoppings/";

        // list of filenames is generated and injected into constructor - ShoppingService

        var filenames = Stream.generate(() -> path + atomicInteger.getAndIncrement() + ".json")
                .limit(8)
                .toList();

        var shoppingService = new ShoppingService(filenames);

        var routing = new Routing(shoppingService);

        routing.routes();

    }

}
