package domain.customer.converter;

import domain.config.converter.JsonConverter;
import domain.customer.Customer;


public class CustomerConverter extends JsonConverter<Customer> {
    public CustomerConverter(String message) {
        super(message);
    }
}
