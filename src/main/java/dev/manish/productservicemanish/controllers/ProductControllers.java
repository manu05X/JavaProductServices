package dev.manish.productservicemanish.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
public class ProductControllers {

    @GetMapping("/products")
    public String getAllProducts() {
        return "Geting All Product";
    }

    @GetMapping("/products/{productId}")
    public String getSingleProduct(@PathVariable("productId") Long productId) {
        return "Geting Single Product : " + productId;
    }

    @PostMapping("/products")
    public String addNewProduct() {
        return "Adding New Product. ";
    }

    @PutMapping("/products/{productId}")
    public String updateProduct(@PathVariable("productId") Long productId) {
        return "Updating Product : " + productId;
    }

    @DeleteMapping("/products/{productId}")
    public String deleteProduct(@PathVariable("productId") Long productId) {
        return "Deleting Product : " + productId;
    }
}
