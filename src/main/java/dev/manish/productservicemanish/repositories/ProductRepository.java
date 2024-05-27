package dev.manish.productservicemanish.repositories;

import dev.manish.productservicemanish.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product save(Product product);

    Product findProductsById(Long id);
}
