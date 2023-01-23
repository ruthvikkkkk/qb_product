package com.example.productservice.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.Map;

@Data
@ToString
@Document(Product.COLLECTION_NAME)
public class Product {

    public static final String COLLECTION_NAME = "headphoneProduct";

    @Indexed(unique = true)
    private String productID;

    private String productName;
    private String productBrand;
    private Map<String, String> productUSP;
    private String imageURL;
    //Integer rating;
    private Category productCategory;
    private Boolean deleteStatus;
    private String productDescription;
    private Date createdDate;
    private Date updatedDate;
    private String createdBy;
    private String updatedBy;
}
