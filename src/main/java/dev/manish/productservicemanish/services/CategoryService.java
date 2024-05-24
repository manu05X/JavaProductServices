package dev.manish.productservicemanish.services;

import dev.manish.productservicemanish.models.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CategoryService {

    List<String> getAllCategories();
    List<Product> getProductsInCategory(String categoryName);
}
