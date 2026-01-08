package com.ecom.E_ComWebAppBackend.services;

import com.ecom.E_ComWebAppBackend.Models.Order;
import com.ecom.E_ComWebAppBackend.Models.OrderItem;
import com.ecom.E_ComWebAppBackend.Models.Product;
import com.ecom.E_ComWebAppBackend.Models.dto.OrderItemRequest;
import com.ecom.E_ComWebAppBackend.Models.dto.OrderItemResponse;
import com.ecom.E_ComWebAppBackend.Models.dto.OrderRequest;
import com.ecom.E_ComWebAppBackend.Models.dto.OrderResponse;
import com.ecom.E_ComWebAppBackend.customeExceptions.InsufficientStockException;
import com.ecom.E_ComWebAppBackend.customeExceptions.ProductNotFoundException;
import com.ecom.E_ComWebAppBackend.dao.OrderRepo;
import com.ecom.E_ComWebAppBackend.dao.ProductRepo;
import com.ecom.E_ComWebAppBackend.enums.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class OrderService {

    @Autowired
    private OrderRepo repo;

    @Autowired
    private ProductRepo productRepo;

    public List<OrderResponse> getAllOrders() {
        List<Order> orders = repo.findAll();
        List<OrderResponse> orderResponses = new ArrayList<>();
        for(Order order:orders)
        {
            orderResponses.add(getOrderResponseFromOrder(order));
        }
        return orderResponses;
    }


    public OrderResponse addOrder(OrderRequest orderRequest) {
        Order order = new Order();
        String timestamp = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        int random = new Random().nextInt(8999)+1000;
        String orderId = "ORD"+timestamp+random;
        order.setOrderId(orderId);
        order.setCustomerName(orderRequest.name());
        order.setEmail(orderRequest.email());
        order.setStatus(OrderStatus.PROCESSING);
        order.setDate(LocalDate.now());

        List<OrderItemRequest> orderItemRequests = orderRequest.orderItemRequests();
        List<OrderItem> orderItems = new ArrayList<>();
        for(OrderItemRequest orderItemRequest:orderItemRequests)
        {
            Product product = productRepo.findById(orderItemRequest.id()).orElseThrow(()-> new ProductNotFoundException("Product with ID " + orderItemRequest.id() + " not found"));
            if(product.getStockQuantity()<orderItemRequest.quantity()) throw new InsufficientStockException("Requested quantity (" + orderItemRequest.quantity() + ") exceeds available stock (" + product.getStockQuantity() + ")");
            product.setStockQuantity(product.getStockQuantity()-orderItemRequest.quantity());
            productRepo.save(product);
            OrderItem orderItem = OrderItem.builder()
                            .product(product)
                            .quantity(orderItemRequest.quantity())
                            .totalPrice(product.getPrice().multiply(BigDecimal.valueOf(orderItemRequest.quantity())))
                            .order(order)
                            .build();
            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);
        Order savedOrder = repo.save(order);

        return getOrderResponseFromOrder(savedOrder);
    }

    private OrderResponse getOrderResponseFromOrder(Order order)
    {
        List<OrderItemResponse> orderItemResponses = new ArrayList<>();
        for(OrderItem orderItem:order.getOrderItems()) {
            OrderItemResponse orderItemResponse = new OrderItemResponse(
                    orderItem.getProduct().getName(),
                    orderItem.getQuantity(),
                    orderItem.getTotalPrice()
            );
            orderItemResponses.add(orderItemResponse);
        }
        return new OrderResponse(
                order.getOrderId(),
                order.getCustomerName(),
                order.getEmail(),
                order.getStatus(),
                order.getDate(),
                orderItemResponses
        );
    }

}
