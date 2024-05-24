package dev.manish.productservicemanish.services;

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

    RestTemplateBuilder restTemplateBuilder;

    public FakeStoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    private  <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod,String url, @Nullable Object request,
                                               Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

    /*
//    @Override
//    public List<Product> getAllProducts() {
//        RestTemplate restTemplate = restTemplateBuilder.build();
//        ResponseEntity<List> response= restTemplate.getForEntity(
//                "https://fakestoreapi.com/products",
//                List.class); // here we have not specified any short what from of list it is so java assume it to be object of LinkedHashMap
//        //By default restTemplate tried to convert whatever object it gets into a HashMap rather than converting it into object of real class
//
//        List<Product> answer = new ArrayList<>();
//        for (Object object : response.getBody()) {
//            ProductDto productDto = (ProductDto) object;
//
//            Product product = new Product();
//
//            product.setId(productDto.getId());
//            product.setTitle(productDto.getTitle());
//            product.setPrice(productDto.getPrice());
//
//            Category category = new Category();
//            category.setName(productDto.getCategory());
//            product.setCategory(category);
//
//            product.setImageUrl(productDto.getImage());
//
//            answer.add(product);
//        }
//
//
//        //return null;
//        return answer;
//    }*/

    @Override
    public List<Product> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response= restTemplate.getForEntity(
                "https://fakestoreapi.com/products",
                FakeStoreProductDto[].class);

        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto : response.getBody()) {
            /*
            Product product = new Product();

            product.setId(fakeStoreProductDto.getId());
            product.setTitle(fakeStoreProductDto.getTitle());
            product.setPrice(fakeStoreProductDto.getPrice());

            Category category = new Category();
            category.setName(fakeStoreProductDto.getCategory());

            product.setCategory(category);
            product.setImageUrl(fakeStoreProductDto.getImage());
             */
            products.add(convertFakeStoreProductDtoToProduct(fakeStoreProductDto));
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
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {
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
        //But we need to return Product not productDto
        //So Convert it into product
        /*
        Product product = new Product();

        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());

        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);

        product.setImageUrl(fakeStoreProductDto.getImage());
         */

        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);

    }

    @Override
    public Product addNewProduct(ProductDto product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(
                "https://fakestoreapi.com/products",
                    product,
                FakeStoreProductDto.class
                );
/*
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

*/
        FakeStoreProductDto fakeStoreProductDto = response.getBody();

        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImage(product.getImageUrl());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setCategory(product.getCategory().getName());

//        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = requestForEntity(
//                HttpMethod.PATCH,
//                "https://fakestoreapi.com/products/{id}",
//                fakeStoreProductDto,
//                FakeStoreProductDto.class,
//                productId
//                );
//

        //return convertFakeStoreProductDtoToProduct(fakeStoreProductDtoResponseEntity.getBody());

        FakeStoreProductDto fakeStoreProductDtoResponse = restTemplate.patchForObject(
                "https://fakestoreapi.com/products/{id}",
                fakeStoreProductDto,
                FakeStoreProductDto.class,
                productId
        );

        return convertFakeStoreProductDtoToProduct(fakeStoreProductDtoResponse);


    }

    @Override
    public Product replaceProduct(Long productId, ProductDto product) {
        return null;
    }

    /*
    @Override
    public boolean deleteProduct(Long productId) {
        ResponseEntity<Void> responseEntity = requestForEntity(
                HttpMethod.DELETE,
                "https://fakestoreapi.com/products/{id}",
                null,
                Void.class,
                productId
        );

        // Check if the delete operation was successful
        return responseEntity.getStatusCode().is2xxSuccessful();
    }
     */


    @Override
    public boolean deleteProduct(Long productId) {
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
