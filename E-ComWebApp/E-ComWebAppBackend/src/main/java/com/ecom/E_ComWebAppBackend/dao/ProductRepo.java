package com.ecom.E_ComWebAppBackend.dao;

import com.ecom.E_ComWebAppBackend.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {

    @Query("SELECT p FROM Product p WHERE " +
            "LOWER(p.name) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
            "LOWER(p.description) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
            "LOWER(p.brand) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
            "LOWER(p.category) LIKE LOWER(CONCAT('%', :searchWord, '%'))")
    List<Product> findByKeyword(@Param("searchWord") String keyword);
}
