package domain.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@Builder

public class Customer {

    String firstName;
    String lastName;
    int Age;
    BigDecimal cash;

}
