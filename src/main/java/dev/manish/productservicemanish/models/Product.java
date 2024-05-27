package dev.manish.productservicemanish.models;

import dev.manish.productservicemanish.dto.ProductDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends BaseModel{
    private String title;
    private double price;
    private String description;
    //@ManyToOne(cascade = {CascadeType.PERSIST})
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;
    private String imageUrl;
}

/**
 * Relation btween Product and Category
 *
 * P : C
 * 1 -> 1
 * m <- 1
 *
 * m <-> 1  Ans
 *
 * */