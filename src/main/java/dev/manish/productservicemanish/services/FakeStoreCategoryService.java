package dev.manish.productservicemanish.services;


import dev.manish.productservicemanish.client.fakestoreapi.FakeStoreProductDto;
import dev.manish.productservicemanish.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FakeStoreCategoryService implements CategoryService{
    private  RestTemplateBuilder restTemplateBuilder;

    public FakeStoreCategoryService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public List<String> getAllCategories() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<String[]> response = restTemplate.getForEntity(
                "https://fakestoreapi.com/products/categories",
                String[].class);
        return Arrays.asList(response.getBody());
    }

    @Override
    public List<Product> getProductsInCategory(String categoryName) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(
                "https://fakestoreapi.com/products/category/{categoryName}",
                FakeStoreProductDto[].class,
                categoryName);
        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto dto : response.getBody()) {
            products.add(convertDtoToProduct(dto));
        }
        return products;
    }

    private Product convertDtoToProduct(FakeStoreProductDto dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setTitle(dto.getTitle());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());
        product.setImageUrl(dto.getImage());
        return product;
    }
}
