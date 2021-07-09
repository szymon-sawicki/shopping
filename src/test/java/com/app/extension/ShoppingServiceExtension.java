package com.app.extension;

import com.app.domain.customer.Customer;
import com.app.service.ShoppingService;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;


import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class ShoppingServiceExtension implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(ShoppingService.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {

        AtomicInteger atomicInteger = new AtomicInteger(1);
        var path = "src/main/resources/shoppings/";

        // list of filenames is generated and injected into constructor - ShoppingService

        var filenames = Stream.generate(() -> path + atomicInteger.getAndIncrement() + ".json")
                .limit(8)
                .toList();

        return new ShoppingService(filenames);

    }

}
