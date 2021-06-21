package domain.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.math.BigDecimal;

@AllArgsConstructor
@Builder

/**
 * Domain class representing customer, contains few simple properties
 * @Author Szymon Sawicki
 */

public class Customer {

    String firstName;
    String lastName;
    int age;
    BigDecimal cash;

}
