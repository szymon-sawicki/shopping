package com.app.domain.product;

import com.app.domain.config.validator.Validator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * implementation of Validator interface used to validate Product objects
 * @see com.app.domain.config.validator.Validator
 */

public class ProductValidator implements Validator<Product> {

    @Override
    public Map<String, String> validate(Product product) {

        var errors = new HashMap<String, String>();

        if(Objects.isNull(product)) {
            errors.put("product","is null");
            return errors;
        }

        var name = product.name;

        if(Objects.isNull(name)) {
            errors.put("name","is null");
        }

        if(name.length() < 3) {
            errors.put("name","is too short - " + name.length());
        }

        var category = product.category;

        if(Objects.isNull(category)) {
            errors.put("category","is null");
        }

        var price = product.price;

        if(Objects.isNull(price)) {
            errors.put("price","is null");
        } else if(price.compareTo(BigDecimal.ZERO) < 0) {
                errors.put("price","must have positive value");
            }

        return errors;

    }
}
