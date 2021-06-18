package domain.shopping;

import domain.customer.Customer;
import domain.product.Product;
import domain.shopping.exception.ShoppingException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Objects;

@AllArgsConstructor
@RequiredArgsConstructor
@Builder

public class Shopping {

    String[] filenames;
    HashMap<Customer,HashMap<Product,Integer>> shoppingMap;

    public Shopping(String...files) {
        this.filenames = files;
        init();
    }

    private void init() {
        if(Objects.isNull(filenames)) {
            throw new ShoppingException("filenames cannot be null");
        }

        //todo zrobić metodę zczytującą pliki

    }

}
