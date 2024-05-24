package dev.manish.productservicemanish.client.fakestoreapi;

import dev.manish.productservicemanish.dto.ProductDto;
import dev.manish.productservicemanish.exceptions.ProductNotFoundException;
import dev.manish.productservicemanish.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class FakeStoreClient {
    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    private  <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod,String url, @Nullable Object request,
                                                    Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

    public List<FakeStoreProductDto> getAllProducts(){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response= restTemplate.getForEntity(
                "https://fakestoreapi.com/products",
                FakeStoreProductDto[].class);

        return Arrays.asList(response.getBody());

    }

    public FakeStoreProductDto getSingleProduct(Long productId) throws ProductNotFoundException{
        // RestTemplate restTemplate = restTemplateBuilder.build();// not working

        RestTemplate restTemplate = restTemplateBuilder.requestFactory(
                HttpComponentsClientHttpRequestFactory.class).build();

        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(
                "https://fakestoreapi.com/products/{id}",
                FakeStoreProductDto.class,
                productId
        );

        if(response.getBody() == null){
            throw new ProductNotFoundException("Product with id " + productId + " not found");
        }

        FakeStoreProductDto fakeStoreProductDto = response.getBody(); // this is the response we get in response body

        return fakeStoreProductDto;
    }


    public FakeStoreProductDto addNewProduct(ProductDto product){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(
                "https://fakestoreapi.com/products",
                product,
                FakeStoreProductDto.class
        );
        FakeStoreProductDto fakeStoreProductDto = response.getBody();

        return fakeStoreProductDto;
    }


    public FakeStoreProductDto updateProduct(Long productId, Product product){
        RestTemplate restTemplate = restTemplateBuilder.build();

        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImage(product.getImageUrl());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setCategory(product.getCategory().getName());


        FakeStoreProductDto fakeStoreProductDtoResponse = restTemplate.patchForObject(
                "https://fakestoreapi.com/products/{id}",
                fakeStoreProductDto,
                FakeStoreProductDto.class,
                productId
        );

        return fakeStoreProductDtoResponse;

    }


    public FakeStoreProductDto replaceProduct(Long productId, Product product){
        //RestTemplate restTemplate = restTemplateBuilder.build();

        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImage(product.getImageUrl());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setCategory(product.getCategory().getName());

        ResponseEntity<FakeStoreProductDto> response = requestForEntity(
                HttpMethod.PUT,
                "https://fakestoreapi.com/products/{id}",
                fakeStoreProductDto,
                FakeStoreProductDto.class,
                productId);

        return response.getBody();
    }

    //Product deleteProduct(Long productId);
    public FakeStoreProductDto deleteProduct(Long productId){
        // Prepare a RestTemplate instance to execute HTTP requests
        RestTemplate restTemplate = restTemplateBuilder.build();

        // Prepare a request callback to set the accept header for the HTTP request
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);

        // Prepare a response extractor to extract the response entity from the HTTP response
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);

        // Execute an HTTP DELETE request to the specific product URL with the provided ID
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.execute(
                "https://fakestoreapi.com/products/{id}",             // URL of the specific product to be deleted
                HttpMethod.DELETE,             // HTTP DELETE method
                requestCallback,               // Request callback to set accept header
                responseExtractor,             // Response extractor to extract response entity
                productId                             // ID of the product to be deleted
        );

        // Convert the response body (deleted product) from FakeStoreProductDto to Product object
        FakeStoreProductDto deletedProduct = responseEntity.getBody();


        return deletedProduct;
    }

}
