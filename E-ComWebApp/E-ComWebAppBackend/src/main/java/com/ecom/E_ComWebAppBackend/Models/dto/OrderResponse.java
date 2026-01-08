package com.ecom.E_ComWebAppBackend.Models.dto;

import com.ecom.E_ComWebAppBackend.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record OrderResponse(
        String orderId,
        String customerName,
        String email,
        OrderStatus status,
        LocalDate orderDate,
        List<OrderItemResponse> orderItems) {
}
