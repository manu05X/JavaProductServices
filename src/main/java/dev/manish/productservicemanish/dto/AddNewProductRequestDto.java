package dev.manish.productservicemanish.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddNewProductRequestDto {
    private String title;
    private double price;
    private String description;
    private String image;
    private String category;
}
