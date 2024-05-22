package dev.manish.productservicemanish.services;

import dev.manish.productservicemanish.dto.ProductDto;
import org.springframework.stereotype.Service;

@Service
public class FakeStoreProductServiceImpl implements ProductService{
    @Override
    public String getAllProducts() {
        return "";
    }

    @Override
    public String getSingleProduct(Long productId) {
        return "";
    }

    @Override
    public String addNewProduct(ProductDto productDto) {
        return "";
    }

    @Override
    public String updateProduct(Long productId) {
        return "";
    }

    @Override
    public String deleteProduct(Long productId) {
        return "";
    }
}
