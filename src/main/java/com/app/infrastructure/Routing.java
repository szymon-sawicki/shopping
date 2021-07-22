package com.app.infrastructure;

import com.app.domain.product.type.Category;
import com.app.infrastructure.config.JsonTransformer;
import com.app.infrastructure.dto.ResponseDto;
import com.app.service.ShoppingService;
import com.app.service.exception.ShoppingServiceException;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;

import java.util.Locale;

import static spark.Spark.*;

@RequiredArgsConstructor

/**
 * main controller class used to handle requests
 * @author Szymon Sawicki
 */

public class Routing {

    private final ShoppingService shoppingService;


    public void routes() {

        path("/shopping/", () -> {

            get("/", (request, response) -> {
                response.header("Content-Type", "application/json;charset=utf-8");
                return ResponseDto.toResponse(shoppingService.getShoppingMap());
            }, new JsonTransformer());


            get("/greatest_bill/", (request, response) -> {
                response.header("Content-Type", "application/json;charset=utf-8");
                return ResponseDto.toResponse(shoppingService.greatestBill());

            }, new JsonTransformer());


            get("/greatest_bill/:category", (request, response) -> {
                var category = Category.valueOf(request.params("category").toUpperCase(Locale.ROOT));
                response.header("Content-Type", "application/json;charset=utf-8");
                return ResponseDto.toResponse(shoppingService.greatestBillFromCategory(category));
            }, new JsonTransformer());


            get("/category_statistics/", (request, response) -> {
                response.header("Content-Type", "application/json;charset=utf-8");
                return ResponseDto.toResponse(shoppingService.getMapWithCategoryStatistics());
            }, new JsonTransformer());


            get("/category_statistics/:category", (request, response) -> {
                var category = Category.valueOf(request.params("category").toUpperCase(Locale.ROOT));
                response.header("Content-Type", "application/json;charset=utf-8");
                return ResponseDto.toResponse(shoppingService.getCategoryStats(category));
            }, new JsonTransformer());


            get("/customers_cash/", (request, response) -> {
                response.header("Content-Type", "application/json;charset=utf-8");
                return ResponseDto.toResponse(shoppingService.customersCashAfterShopping());
            }, new JsonTransformer());

        });

        /**
         * exception handling
         */


        exception(ShoppingServiceException.class, (exception, request, response) -> {
            response.redirect("/error/" + exception.getMessage(), 301);
        });

        path("/error/", () -> {
            get("/:message",
                    (request, response) -> {
                        var message = request.params(":message");
                        response.header("Content-type", "application/json;charset=utf-8");
                        response.status(500);
                        var responseBody = ResponseDto.toError(message);
                        return toJson(responseBody);
                    }, new JsonTransformer()
            );


        });

    }

    private static <T> String toJson(T data) {
        var gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(data);
    }

}
