package com.ecom.E_ComWebAppBackend.controllers;

import com.ecom.E_ComWebAppBackend.Models.dto.OrderRequest;
import com.ecom.E_ComWebAppBackend.Models.dto.OrderResponse;
import com.ecom.E_ComWebAppBackend.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api")
public class OrderController {
    @Autowired
    private OrderService service;

    @GetMapping("orders")
    public ResponseEntity<List<OrderResponse>> getAllOrders()
    {
        return ResponseEntity.ok(service.getAllOrders());
    }

    @PostMapping("order")
    public ResponseEntity<OrderResponse> addOrder(@RequestBody OrderRequest orderRequest)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addOrder(orderRequest));
    }
}
