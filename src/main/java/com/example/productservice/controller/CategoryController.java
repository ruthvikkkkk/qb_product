package com.example.productservice.controller;

import com.example.productservice.DTO.CategoryDTO;
import com.example.productservice.entity.Category;
import com.example.productservice.service.CategoryServiceInterface;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryServiceInterface categoryService;

    @PostMapping("/add")
    public ResponseEntity<CategoryDTO> addCategory(@RequestBody CategoryDTO categoryDTO){
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);
        category.setCategoryId(category.getCategoryName().toUpperCase().substring(0,3));
        return new ResponseEntity<>(categoryService.addCategory(category), HttpStatus.CREATED);
    }

    @GetMapping("/findall")
    public ResponseEntity<List<CategoryDTO>> findAllCategories(){
        return new ResponseEntity<>(categoryService.findAllCategories(), HttpStatus.OK);
    }

    @GetMapping("/findallcategoryid")
    public ResponseEntity<List<String>> findAllCategoryId(){
        List<CategoryDTO> categories = categoryService.findAllCategories();
        List<String> categoryIds = new ArrayList<>();
        for(CategoryDTO c : categories){
            categoryIds.add(c.getCategoryId());
        }

        return new ResponseEntity<>(categoryIds, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteCategory(@PathVariable("id") String categoryId){
        return new ResponseEntity<>(categoryService.deleteCategory(categoryId), HttpStatus.OK);
    }
}
