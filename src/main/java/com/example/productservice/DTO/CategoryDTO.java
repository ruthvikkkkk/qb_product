package com.example.productservice.DTO;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@ToString
public class CategoryDTO {

    @Indexed(unique = true)
    private String categoryId;
    private String categoryName;
    private Boolean deleteStatus;
}