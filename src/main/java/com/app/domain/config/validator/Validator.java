package com.app.domain.config.validator;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * interface used to validate objects
 * @param <T> type of object to validate
 */

public interface Validator<T> {

    /**
     * abstract method that will be implemented to validate given object
     * @param t object to validate
     * @return map of errors with validated element as key and error message as value
     */
    Map<String,String>  validate(T t);


    /**
     *
     * @param validator implementation of Validator interface, which validate object of given type
     * @param t object to validate
     * @param <T> type of object to validate
     */

    static <T> void validate(Validator<T> validator, T t) {

        var errors = validator.validate(t);

        if(!errors.isEmpty()) {
            var message = errors
                    .entrySet()
                    .stream()
                    .map(e -> e.getKey() + ": " + e.getValue())
                    .collect(Collectors.joining(", "));

            throw new ValidatorException("[VALIDATION ERRORS]: " + message);

        }

    }


}
