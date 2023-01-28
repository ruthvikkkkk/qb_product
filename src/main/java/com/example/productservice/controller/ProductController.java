package com.example.productservice.controller;

import com.example.productservice.DTO.CategoryDTO;
import com.example.productservice.DTO.ProductDTO;
import com.example.productservice.DTO.ProductSearchDTO;
import com.example.productservice.entity.Product;
import com.example.productservice.service.CategoryServiceInterface;
import com.example.productservice.service.ProductServiceInterface;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
//        CategoryDTO category = new CategoryDTO();
//
        BeanUtils.copyProperties(productDTO, product);
//        product.setProductID(product.getProductCategory().getCategoryName().toUpperCase().substring(0,3) +  new Date().getTime());
//
//        BeanUtils.copyProperties(product.getProductCategory(), category);
//        product.getProductCategory().setCategoryId(category.getCategoryName().toUpperCase().substring(0,3));
//
//        if(!categoryService.existsById(product.getProductCategory().getCategoryId())) {
//            categoryService.addCategory(product.getProductCategory());
//        }
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

    @GetMapping(value = "/searchProduct/{searchTerm}")
    public ResponseEntity<List<ProductSearchDTO>> getProductBySearch(@PathVariable("searchTerm") String searchTerm) {
        List<Product> productList = productService.getProductBySearch(searchTerm);
        List<ProductSearchDTO> productSearchDTOS = new ArrayList<>();
        for (Product product : productList) {
            ProductSearchDTO productSearchDTO = new ProductSearchDTO();
            BeanUtils.copyProperties(product, productSearchDTO);
            productSearchDTOS.add(productSearchDTO);
        }
        return new ResponseEntity<List<ProductSearchDTO>>(productSearchDTOS, HttpStatus.OK);
    }
}
