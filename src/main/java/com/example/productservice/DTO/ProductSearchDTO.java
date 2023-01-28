package com.example.productservice.DTO;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProductSearchDTO {
    private String productName;
    private String productBrand;
    private String highBass;
    private String waterResistant;
    private String aptX;

}