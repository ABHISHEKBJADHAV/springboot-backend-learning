package com.ecom.E_ComWebAppBackend.Models.dto;

import java.math.BigDecimal;

public record OrderItemResponse(String productName, int quantity, BigDecimal totalPrice) {
}
