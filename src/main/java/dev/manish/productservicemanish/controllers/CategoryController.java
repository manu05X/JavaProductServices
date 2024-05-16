package dev.manish.productservicemanish.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
    @GetMapping("/categories")
    public String getAllCategories() {
        return "Getting all categories";
    }

    public String getProductsInCategory() {
        return "Getting product in category";
    }
}
