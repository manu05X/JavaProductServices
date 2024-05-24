package dev.manish.productservicemanish.services;

import dev.manish.productservicemanish.client.fakestoreapi.FakeStoreClient;
import dev.manish.productservicemanish.client.fakestoreapi.FakeStoreProductDto;
import dev.manish.productservicemanish.dto.ProductDto;
import dev.manish.productservicemanish.exceptions.ProductNotFoundException;
import dev.manish.productservicemanish.models.Category;
import dev.manish.productservicemanish.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductServiceImpl implements ProductService{

    private RestTemplateBuilder restTemplateBuilder;

    private FakeStoreClient fakeStoreClient;

    public FakeStoreProductServiceImpl(FakeStoreClient fakeStoreClient) {
        this.fakeStoreClient = fakeStoreClient;
    }

    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request,
                                                   Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }


    @Override
    public List<Product> getAllProducts() {
        List<FakeStoreProductDto> fakeStoreProductDtos = fakeStoreClient.getAllProducts();

        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {

            products.add(convertFakeStoreProductDtoToProduct(fakeStoreProductDto));
        }
        return products;

    }



    /*
     * It will return Product Obj with all details of fetched product.
     * The id of the catogery will be null but the name of the category will be correct.
     *
     */

    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {

        FakeStoreProductDto fakeStoreProductDto = fakeStoreClient.getSingleProduct(productId);

        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);

    }

    @Override
    public Product addNewProduct(ProductDto product) {
        FakeStoreProductDto fakeStoreProductDto = fakeStoreClient.addNewProduct(product);

        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }


    @Override
    public Product updateProduct(Long productId, Product product) {
       FakeStoreProductDto fakeStoreProductDto = fakeStoreClient.updateProduct(productId, product);
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);


    }

    @Override
    public Product replaceProduct(Long productId, Product product) {
        //RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDto fakeStoreProductDto = fakeStoreClient.replaceProduct(productId, product);

       return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }


    @Override
    public boolean deleteProduct(Long productId) {
        FakeStoreProductDto fakeStoreProductDto = fakeStoreClient.deleteProduct(productId);
        // Convert the response body (deleted product) from FakeStoreProductDto to Product object
        FakeStoreProductDto deletedProduct = fakeStoreProductDto;

        if(deletedProduct != null) {
            return true;
        } else {
            return false;
        }
        // Return the deleted product
        // return convertFakeStoreProductDtoToProduct(deletedProduct);
    }

    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();

        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());

        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);

        product.setImageUrl(fakeStoreProductDto.getImage());

        return product;
    }
}
