package com.example.productservice.controller;

import com.example.productservice.DTO.ProductDTO;
import com.example.productservice.entity.Category;
import com.example.productservice.entity.Product;
import com.example.productservice.service.CategoryServiceInterface;
import com.example.productservice.service.ProductServiceInterface;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductServiceInterface productService;

    @Autowired
    CategoryServiceInterface categoryService;

    @PostMapping("/add")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO){
        Product product = new Product();
        Category category = new Category();
        //categoryController.addCategory(productDTO.getProductCategoryDTO());
        BeanUtils.copyProperties(productDTO, product);
        BeanUtils.copyProperties(productDTO.getProductCategoryDTO(), category);
        categoryService.addCategory(category);
        product.setProductCategory(category);
        String generatedID = productDTO.getProductCategoryDTO().getCategoryName().toUpperCase().substring(0,3) + (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()));
        product.setProductID(generatedID);
        product.setCreatedDate(new Date());
        return new ResponseEntity<>(productService.addOrUpdateProduct(product), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable("id") String productID){
        return new ResponseEntity<>(productService.deleteProduct(productID), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable("id") String productID){
        return new ResponseEntity<>(productService.getSingleProduct(productID), HttpStatus.OK);
    }

    @GetMapping("/get/category/{id}")
    public ResponseEntity<List<ProductDTO>> getProductByCategory(@PathVariable("id") String categoryId){
        return new ResponseEntity<>(productService.getProductByCategory(categoryId), HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Product>> getAll(){
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }
}
