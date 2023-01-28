package com.example.productservice.DTO;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProductSearchDTO {
    private String productName;
    private String productBrand;
    private String feature1;
    private String feature2;
    private String feature3;
}