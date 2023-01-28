package com.example.productservice.service.impl;

import com.example.productservice.DTO.CategoryDTO;
import com.example.productservice.DTO.ProductDTO;
import com.example.productservice.entity.Product;
import com.example.productservice.repository.CustomProductRepository;
import com.example.productservice.repository.ProductRepository;
import com.example.productservice.service.CategoryServiceInterface;
import com.example.productservice.service.ProductServiceInterface;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImplementation implements ProductServiceInterface {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryServiceInterface categoryService;

    @Autowired
    CustomProductRepository customProductRepository;

    @Override
    public ProductDTO addOrUpdateProduct(Product product) {
        ProductDTO productDTO = new ProductDTO();
        //Product product = new Product();
        CategoryDTO category = new CategoryDTO();

        //BeanUtils.copyProperties(productDTO, product);
        product.setProductID(product.getProductCategory().getCategoryName().toUpperCase().substring(0,3) + new Date().getTime());

        BeanUtils.copyProperties(product.getProductCategory(), category);
        product.getProductCategory().setCategoryId(category.getCategoryName().toUpperCase().substring(0,3));

        if(!categoryService.existsById(product.getProductCategory().getCategoryId())) {
            categoryService.addCategory(product.getProductCategory());
        }
        BeanUtils.copyProperties(productRepository.save(product), productDTO);
        return productDTO;
    }

    @Override
    public Boolean deleteProduct(String productId) {
        if(productRepository.existsById(productId)){
            productRepository.deleteById(productId);
            return true;
        }
        return false;
    }

    @Override
    public ProductDTO getSingleProduct(String productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(optionalProduct.isPresent()) {
            ProductDTO productDTO = new ProductDTO();
            BeanUtils.copyProperties(optionalProduct.get(), productDTO);
            return productDTO;
        }
        return null;
    }

    @Override
    public List<ProductDTO> getProductByCategory(String categoryId) {
        List<ProductDTO> productByCategory = new ArrayList<>();
//        for(Product product : productRepository.findAll()){
//            if(product.getProductCategory().getCategoryId().equals(categoryId)){
//                ProductDTO productDTO = new ProductDTO();
//                BeanUtils.copyProperties(product, productDTO);
//                productByCategory.add(productDTO);
//            }
//        }

        for(Product product : productRepository.findByProductCategory_CategoryId(categoryId)){
            ProductDTO productDTO = new ProductDTO();
            BeanUtils.copyProperties(product, productDTO);
            productByCategory.add(productDTO);
        }

        return productByCategory;
    }

    public List<Product> getAll(){
        return productRepository.findAll();
    }

    public List<Product> getProductBySearch(String searchTerm) {
        return customProductRepository.searchByRegex(searchTerm);
    }
}
