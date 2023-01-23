package com.example.productservice.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@ToString
@Document(Category.COLLECTION_NAME)
public class Category {

    public static final String COLLECTION_NAME = "headphonesCategory";

    @Id
    //@Indexed(unique = true)
    private String categoryId;
    private String categoryName;
    private Boolean deleteStatus;
}
