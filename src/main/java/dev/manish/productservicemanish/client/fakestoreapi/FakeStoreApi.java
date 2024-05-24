package dev.manish.productservicemanish.client.fakestoreapi;

import dev.manish.productservicemanish.dto.ProductDto;
import dev.manish.productservicemanish.exceptions.ProductNotFoundException;
import dev.manish.productservicemanish.models.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FakeStoreApi {
    List<FakeStoreProductDto> getAllProducts(){
        return null;
    }

    FakeStoreProductDto getSingleProduct(Long productId) throws ProductNotFoundException{
        return null;
    }


    FakeStoreProductDto addNewProduct(ProductDto product){
        return null;
    }


    FakeStoreProductDto updateProduct(Long productId, Product product){
        return null;

    }
    FakeStoreProductDto replaceProduct(Long productId, ProductDto product){
        return null;
    }

    //Product deleteProduct(Long productId);
    FakeStoreProductDto deleteProduct(Long productId){
        return null;
    }
}
