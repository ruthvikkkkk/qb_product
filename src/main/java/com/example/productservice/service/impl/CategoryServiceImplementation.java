package com.example.productservice.service.impl;

import com.example.productservice.DTO.CategoryDTO;
import com.example.productservice.entity.Category;
import com.example.productservice.repository.CategoryRepository;
import com.example.productservice.service.CategoryServiceInterface;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImplementation implements CategoryServiceInterface {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryDTO> findAllCategories() {
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        for (Category category : categoryRepository.findAll()) {
            CategoryDTO categoryDTO = new CategoryDTO();
            BeanUtils.copyProperties(category, categoryDTO);
            categoryDTOList.add(categoryDTO);
        }

        return categoryDTOList;
    }

    @Override
    public CategoryDTO addCategory(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        BeanUtils.copyProperties(categoryRepository.save(category), categoryDTO);
        return categoryDTO;
    }

    @Override
    public Boolean deleteCategory(String categoryId) {
        if (categoryRepository.existsById(categoryId)) {
            categoryRepository.deleteById(categoryId);
            return true;
        }
        return false;
    }

    @Override
    public Boolean existsById(String categoryId) {
        List<Category> categoryList = categoryRepository.findAll();
        for (Category c : categoryList) {
            if (c.getCategoryId().equals(categoryId)) {
                return true;
            }
        }
        return false;
    }
}
