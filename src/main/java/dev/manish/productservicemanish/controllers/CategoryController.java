package dev.manish.productservicemanish.controllers;

import dev.manish.productservicemanish.models.Product;
import dev.manish.productservicemanish.services.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class CategoryController {

    public CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public List<String> getAllCategories() {
        //return "Getting all categories";
        return categoryService.getAllCategories();
    }
    //category/jewelery
    @GetMapping("/category/{categoryName}")
    public List<Product> getProductsInCategory(@PathVariable("categoryName") String categoryName) {
        //return "Getting product in category";
        return categoryService.getProductsInCategory(categoryName);
    }
}
