package dev.manish.productservicemanish.controllers;

import dev.manish.productservicemanish.dto.GetSingleProductResponseDto;
import dev.manish.productservicemanish.dto.ProductDto;
import dev.manish.productservicemanish.models.Product;
import dev.manish.productservicemanish.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductControllers {

    private ProductService productService;

    public ProductControllers(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public String getAllProducts() {
        return "Geting All Product";
    }

//    @GetMapping("/{productId}")
//    public Product getSingleProduct(@PathVariable("productId") Long productId) {
////        GetSingleProductResponseDto responseDto = new GetSingleProductResponseDto();
////        responseDto.setProduct(
//           return productService.getSingleProduct(productId);
//        //return "Geting Single Product : " + productId;
//        //return responseDto;
//    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("productId") Long productId) {
//        GetSingleProductResponseDto responseDto = new GetSingleProductResponseDto();
//        responseDto.setProduct(
        //Map<String,String> headers = new HashMap<>();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String,String>();
        headers.add("auth-token","noaccess4uheyhey");

        //along with body we are also sending header and status in response
        ResponseEntity<Product> response = new ResponseEntity(
                productService.getSingleProduct(productId), headers,
                HttpStatus.NOT_FOUND);




        return response;
        //return productService.getSingleProduct(productId);
        //return "Geting Single Product : " + productId;
        //return responseDto;
    }

    @PostMapping()
    public String addNewProduct(@RequestBody ProductDto productDto) {
        return "Adding New Product : "+ productDto;
    }

    @PutMapping("/{productId}")
    public String updateProduct(@PathVariable("productId") Long productId) {
        return "Updating Product : " + productId;
    }

    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable("productId") Long productId) {
        return "Deleting Product : " + productId;
    }
}
