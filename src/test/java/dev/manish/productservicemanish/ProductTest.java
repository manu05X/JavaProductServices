package dev.manish.productservicemanish;

import dev.manish.productservicemanish.models.Category;
import dev.manish.productservicemanish.models.Product;
import dev.manish.productservicemanish.repositories.CategoryRepository;
import dev.manish.productservicemanish.repositories.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@SpringBootTest
public class ProductTest {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private EntityManager entityManager;


    @Test
    void testFetchingType(){

        //
//        Product product = new Product();
//        product.setPrice(100);
//        product.setImageUrl("hello");
//        product.setCategory(category);
//        productRepository.save(product);

        Category category = new Category();
        category.setName("electronics");
//        Category savedCategory = categoryRepository.save(category);

        Product product = new Product();
        product.setPrice(101);
        product.setImageUrl("hiii");
        product.setCategory(category);
        productRepository.save(product);
    }

    @Test
    @Transactional
    void fetchTypesTest() {
        Product product = productRepository.findProductsById(1L);

        System.out.println("Fetched product");

        Category category = product.getCategory();
        String name = category.getName();

    }

    @Test
    @Transactional
    void fetchTypeTest(){
        Product product = productRepository.findProductsById(1L);

        System.out.println("Product is Fetched");
        //After that we fetch category then we will see category in our product
        product.getCategory();
    }

    @Test
    @Transactional
    @Commit
    void saveProductsForCategory() {
        Optional<Category> optionalCategory = categoryRepository.findById(2L);

        Category category;
        if (optionalCategory.isPresent()) {
            category = optionalCategory.get();
        } else {
            // Create a new Category if not found
            category = new Category();
            category.setName("Default Category");
            category = categoryRepository.save(category);
            // Ensure the ID is refreshed after saving
            entityManager.flush();
            entityManager.refresh(category);
        }

        Product product1 = new Product();
        product1.setPrice(1012);
        product1.setImageUrl("hiii");
        product1.setCategory(category);
        productRepository.save(product1);

        Product product2 = new Product();
        product2.setPrice(103);
        product2.setImageUrl("hiii");
        product2.setCategory(category);
        productRepository.save(product2);

        Product product3 = new Product();
        product3.setPrice(104);
        product3.setImageUrl("Hello");
        product3.setCategory(category);
        productRepository.save(product3);

        // Ensure the data is committed and flushed to the database
        entityManager.flush();
    }


    @Test
    @Transactional
    void getProductForCategory() {
        Category category = categoryRepository.findById(2L).get();

        for (Product product : category.getProducts()) {
            System.out.println(product.getTitle());
        }
    }
}
