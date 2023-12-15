package com.moe.orderservice.service;

import com.moe.orderservice.model.CreateOrderRequest;
import com.moe.orderservice.model.RetrieveOrderDTO;

import java.util.Optional;
import java.util.UUID;

public interface IOrderService {
    Optional<RetrieveOrderDTO> placeOrder(CreateOrderRequest request);
    RetrieveOrderDTO getOrderDetails(UUID id);
}
