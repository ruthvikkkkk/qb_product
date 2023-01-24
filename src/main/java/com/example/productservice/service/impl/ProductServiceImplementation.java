package com.example.productservice.service.impl;

import com.example.productservice.DTO.ProductDTO;
import com.example.productservice.entity.Product;
import com.example.productservice.repository.ProductRepository;
import com.example.productservice.service.ProductServiceInterface;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImplementation implements ProductServiceInterface {

    @Autowired
    ProductRepository productRepository;

    @Override
    public ProductDTO addOrUpdateProduct(Product product) {
        ProductDTO productDTO = new ProductDTO();
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
}
