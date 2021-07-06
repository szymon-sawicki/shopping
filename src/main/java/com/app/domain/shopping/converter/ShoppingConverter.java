package com.app.domain.shopping.converter;

import com.app.domain.config.converter.JsonConverter;
import com.app.domain.shopping.Shopping;

/**
 * child class of converter class used to coverts Shopping objects
 * @see JsonConverter
 * @Author Szymon Sawicki
 */


public class ShoppingConverter extends JsonConverter<Shopping> {
    public ShoppingConverter(String message) {
        super(message);
    }
}
