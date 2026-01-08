package com.ecom.E_ComWebAppBackend.Models.dto;

import java.util.List;

public record OrderRequest(
        String name,
        String email,
        List<OrderItemRequest> orderItemRequests
) { }
