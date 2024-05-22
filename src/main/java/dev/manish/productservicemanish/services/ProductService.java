package dev.manish.productservicemanish.services;

import dev.manish.productservicemanish.dto.ProductDto;
import dev.manish.productservicemanish.models.Category;
import dev.manish.productservicemanish.models.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Product getSingleProduct(Long productId);


    Product addNewProduct(
            String title,
            double price,
            String description,
            Category category,
            String imageUrl
    );

    Product updateProduct(Long productId, Product product);

    boolean deleteProduct(Long productId);
}
