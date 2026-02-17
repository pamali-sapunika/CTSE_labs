package com.sliit.ctse.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sliit.ctse.model.Product;
import com.sliit.ctse.repository.ProductRepository;
import com.sliit.ctse.service.ProductService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

    
    private final ProductRepository productRepo;
    private final ProductService productService;

    public ProductController(ProductRepository productRepo, ProductService productService){
        this.productRepo = productRepo;
        this.productService = productService;
    }

    @PostMapping("/check")
    public ResponseEntity<?> createproduct(@RequestBody Product product){
        return new ResponseEntity<>(productRepo.save(product), HttpStatus.CREATED);
    }

    @PostMapping("/")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){ 
        return new ResponseEntity<>(productService.createProduct(product), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<?> getProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id){
        Product product = productService.getProductById(id);
        if(product == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
        public ResponseEntity<?> deleteSeason(@PathVariable Long id){
        try{
            productService.deleteProduct(id);
            return new ResponseEntity<>("Season with ID " + id + " deleted successfully", HttpStatus.OK);
        }catch(EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
