package domain.customer.converter;

import domain.config.converter.JsonConverter;
import domain.customer.Customer;

/**
 * child class of JsonConverter used to converting customer objects
 * @author Szymon Sawicki
 */

public class CustomerConverter extends JsonConverter<Customer> {
    public CustomerConverter(String message) {
        super(message);
    }
}
