package domain.product.converter;

import domain.config.converter.JsonConverter;
import domain.product.Product;

public class ProductConverter extends JsonConverter<Product> {
    public ProductConverter(String message) {
        super(message);
    }
}
