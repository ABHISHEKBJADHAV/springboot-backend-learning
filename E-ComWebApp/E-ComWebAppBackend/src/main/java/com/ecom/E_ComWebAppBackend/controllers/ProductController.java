package com.ecom.E_ComWebAppBackend.controllers;

import com.ecom.E_ComWebAppBackend.Models.Product;
import com.ecom.E_ComWebAppBackend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("products")
    public ResponseEntity<List<Product>> getAllProducts()
    {
        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.ACCEPTED);
    }

    @GetMapping("product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id)
    {
        try
        {
            Product product = service.getProductById(id);
            return ResponseEntity.ok(product);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("product")
    public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile file)
    {
        Product addedProduct = null;
        try {
            addedProduct = service.addProduct(product,file);
            return new ResponseEntity<>(addedProduct, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_MODIFIED);
        }
    }

    @PutMapping("product/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestPart Product product, @RequestPart(required = false) MultipartFile file)
    {
        Product updatedProduct = null;
        try {
            updatedProduct = service.updateProduct(product,file);
            return new ResponseEntity<>(updatedProduct, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping("product/{id}")
    public void deleteProduct(@PathVariable int id)
    {
        service.deleteProduct(id);
    }

    @GetMapping("products/search")
    public ResponseEntity<List<Product>> searchedProduct(@RequestParam String keyword)
    {
        return new ResponseEntity<>(service.getProductsBySearch(keyword), HttpStatus.ACCEPTED);
    }

}
