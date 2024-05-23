package dev.manish.productservicemanish.dto;

import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    @Nullable
    private Long id;
    private String title;
    private double price;
    private String description;
    private String image;
    private String category;
    @Nullable
    private RatingDto rating;

}


/**
* This allows me to change independently the dtos. As it only correspons to FakeStoreProduct
* */