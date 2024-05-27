package dev.manish.productservicemanish;

import dev.manish.productservicemanish.models.Category;
import dev.manish.productservicemanish.models.Product;
import dev.manish.productservicemanish.repositories.CategoryRepository;
import dev.manish.productservicemanish.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductTest {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void testFetchingType(){

        Category category = new Category();

        category.setName("phones");
        Category saveCategory = categoryRepository.save(category);

        Product product = new Product();
        product.setPrice(100);
        product.setImageUrl("hello.com");
        product.setCategory(category);

        productRepository.save(product);

    }

    @Test
    @Transactional
    void fetchTypeTest(){
        Product product = productRepository.findProductsById(1L);

        System.out.println("Product is Fetched");
        //After that we fetch category then we will see category in our product
        product.getCategory();
    }
}
