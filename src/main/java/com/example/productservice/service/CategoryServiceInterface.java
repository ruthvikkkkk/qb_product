package com.example.productservice.service;

import com.example.productservice.DTO.CategoryDTO;
import com.example.productservice.entity.Category;
import java.util.List;

public interface CategoryServiceInterface {
    List<CategoryDTO> findAllCategories();
    CategoryDTO addCategory(Category category);
    Boolean deleteCategory(String categoryId);
}
