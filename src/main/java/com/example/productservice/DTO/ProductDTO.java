package com.example.productservice.DTO;

import com.example.productservice.entity.USP;
import lombok.Data;
import lombok.ToString;
import java.util.Date;

@Data
@ToString
public class ProductDTO {

    private String productName;
    private String productBrand;
    private USP productUSP;
    private String imageURL;
    //Integer rating;
    private CategoryDTO productCategoryDTO;
    private Boolean deleteStatus;
    private String productDescription;
    private Date createdDate;
    //private Date updatedDate;
    private String createdBy;
    //private String updatedBy;
}
