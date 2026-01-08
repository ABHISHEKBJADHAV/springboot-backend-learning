package com.ecom.E_ComWebAppBackend.services;

import com.ecom.E_ComWebAppBackend.Models.Product;
import com.ecom.E_ComWebAppBackend.dao.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public Product getProductById(int id) {
        return  repo.findById(id).orElseThrow(() -> new NoSuchElementException("Product with ID " + id + " not found"));
    }

    public Product addProduct(Product product, MultipartFile file) throws IOException {
        product.setImageName(file.getOriginalFilename());
        product.setImageType(file.getContentType());
        product.setImageData(file.getBytes());
        return repo.save(product);
    }

    public Product updateProduct(Product product, MultipartFile file) throws IOException {
        if(file!=null) {
            product.setImageName(file.getOriginalFilename());
            product.setImageType(file.getContentType());
            product.setImageData(file.getBytes());
        }
        return repo.save(product);
    }


    public List<Product> getProductsBySearch(String keyword) {
        return repo.findByKeyword(keyword);
    }

    public void deleteProduct(int id) {
        repo.deleteById(id);
    }
}
