package dev.manish.productservicemanish.models;

import dev.manish.productservicemanish.dto.ProductDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Getter
@Setter
public class Product extends BaseModel{
    private String title;
    private double price;
    private String description;
    private Category category;
    private String imageUrl;
}
