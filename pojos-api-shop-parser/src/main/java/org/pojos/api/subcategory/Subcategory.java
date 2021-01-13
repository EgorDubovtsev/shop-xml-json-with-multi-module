package org.pojos.api.subcategory;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.pojos.api.entity.Product;

import java.util.List;
@JsonDeserialize(as = SimpleSubcategory.class)
public interface Subcategory {
    void addProduct(Product product);

    List<Product> getProducts();

     String getName();
}
