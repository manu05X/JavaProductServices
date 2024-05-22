package dev.manish.productservicemanish.dto;

import dev.manish.productservicemanish.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetSingleProductResponseDto {
    private Product product;
}
