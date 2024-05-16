package dev.manish.productservicemanish.controllers;

import dev.manish.productservicemanish.services.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product/categories")
public class CategoryController implements CategoryService {
    @Override
    @GetMapping()
    public String getAllCategories() {
        return "Getting all categories";
    }
    @Override
    @GetMapping("/{categoryId}")
    public String getProductsInCategory(@PathVariable("categoryId") Long categoryId) {
        return "Getting product in category";
    }
}
