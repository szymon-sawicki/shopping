package domain.product;

import domain.config.validator.Validator;

import java.util.HashMap;
import java.util.Map;

public class ProductValidator implements Validator<Product> {

    @Override
    public Map<String, String> validate(Product product) {

        var errors = new HashMap<String, String>();


        TODO

        return errors;



    }
}
