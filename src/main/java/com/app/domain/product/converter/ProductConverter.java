package com.app.domain.product.converter;

import com.app.domain.config.converter.JsonConverter;
import com.app.domain.product.Product;

/**
 * child of converter class used to coverts Products objects
 * @see JsonConverter
 * @Author Szymon Sawicki
 */

public class ProductConverter extends JsonConverter<Product> {
    public ProductConverter(String message) {
        super(message);
    }
}
