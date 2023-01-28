package com.example.productservice.repository;

import com.example.productservice.entity.Product;

import java.util.List;

public interface CustomProductRepository {
    public List<Product> searchByRegex(String searchTerm);
}