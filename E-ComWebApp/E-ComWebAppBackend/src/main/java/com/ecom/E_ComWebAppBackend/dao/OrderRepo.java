package com.ecom.E_ComWebAppBackend.dao;

import com.ecom.E_ComWebAppBackend.Models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Order,Integer> {
}
