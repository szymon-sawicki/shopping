package domain.product.converter;

import domain.config.converter.JsonConverter;
import domain.product.Product;

/**
 * child of converter class used to coverts Products objects
 * @Author Szymon Sawicki
 */

public class ProductConverter extends JsonConverter<Product> {
    public ProductConverter(String message) {
        super(message);
    }
}
