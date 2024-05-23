package dev.manish.productservicemanish.services;

import dev.manish.productservicemanish.dto.GetSingleProductResponseDto;
import dev.manish.productservicemanish.dto.ProductDto;
import dev.manish.productservicemanish.models.Category;
import dev.manish.productservicemanish.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductServiceImpl implements ProductService{

    RestTemplateBuilder restTemplateBuilder;

    public FakeStoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public List<Product> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductDto[]> response= restTemplate.getForEntity(
                "https://fakestoreapi.com/products",
                ProductDto[].class);

        List<Product> products = new ArrayList<>();
        for(ProductDto x : response.getBody()) {

            Product product = new Product();

            product.setId(x.getId());
            product.setTitle(x.getTitle());
            product.setPrice(x.getPrice());

            Category category = new Category();
            category.setName(x.getCategory());

            product.setCategory(category);
            product.setImageUrl(x.getImage());

            products.add(product);
        }
        //return null;
        return products;
    }

    /*
     * It will return Product Obj with all details of fetched product.
     * The id of the catogery will be null but the name of the category will be correct.
     *
     */

    @Override
    public Product getSingleProduct(Long productId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductDto> response = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", ProductDto.class, productId);

        ProductDto productDto = response.getBody(); // this is the reponse we get in response body
        //But we need to return Product not productDto
        //So Convert it into product
        Product product = new Product();

        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());

        Category category = new Category();
        category.setName(productDto.getCategory());
        product.setCategory(category);

        product.setImageUrl(productDto.getImage());

        return product;

    }

    @Override
    public Product addNewProduct(ProductDto product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductDto> response = restTemplate.postForEntity(
                "https://fakestoreapi.com/products",
                    product,
                ProductDto.class
                );

        ProductDto productDto = response.getBody();

        Product product1 = new Product();
        //product1.setId(productDto.getId());
        product1.setTitle(productDto.getTitle());
        product1.setPrice(productDto.getPrice());

        Category category = new Category();
        category.setName(productDto.getCategory());
        product1.setCategory(category);

        product1.setImageUrl(productDto.getImage());


        return product1;
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        return null;
    }

    @Override
    public boolean deleteProduct(Long productId) {
        return false;
    }
}
