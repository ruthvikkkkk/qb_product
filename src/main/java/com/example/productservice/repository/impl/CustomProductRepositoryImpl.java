package com.example.productservice.repository.impl;

import com.example.productservice.entity.Product;
import com.example.productservice.repository.CustomProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.regex.Pattern;

@Repository
public class CustomProductRepositoryImpl implements CustomProductRepository {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Product> searchByRegex(String searchTerm) {
        searchTerm = "."+searchTerm+".";
        Criteria criteria = new Criteria().orOperator(
                Criteria.where("productName").regex(searchTerm, "i"),
                Criteria.where("productBrand").regex(searchTerm, "i"),
                Criteria.where("feature1").regex(searchTerm, "i"),
                Criteria.where("feature2").regex(searchTerm, "i"),
                Criteria.where("feature3").regex(searchTerm, "i"));
        List<Product> productList = mongoTemplate.find(Query.query(criteria), Product.class);
        return productList;
    }
}