package com.sliit.ctse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sliit.ctse.model.Product;
import com.sliit.ctse.repository.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

    private final ProductRepository productRepo;

    @Autowired
    public ProductService(ProductRepository productRepo){
        this.productRepo = productRepo;
    }

    public Product createProduct(Product product){
        return productRepo.save(product);
    }

    public List<Product> getAllProducts(){
        return productRepo.findAll();
    }

    public Product getProductById(Long id){
        return productRepo.findById(id).orElse(null);
    }

    public void deleteProduct(Long id){
        if(!productRepo.existsById(id)) {
            throw new EntityNotFoundException("Product with ID " + id + " is not found");
        }

        productRepo.deleteById(id);
    }

}
