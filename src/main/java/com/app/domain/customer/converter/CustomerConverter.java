package com.app.domain.customer.converter;

import com.app.domain.config.converter.JsonConverter;
import com.app.domain.customer.Customer;

/**
 * child class of JsonConverter used to converting customer objects
 * @see JsonConverter
 * @author Szymon Sawicki
 */

public class CustomerConverter extends JsonConverter<Customer> {
    public CustomerConverter(String message) {
        super(message);
    }
}
