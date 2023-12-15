package com.moe.orderservice.controller;

import com.moe.orderservice.model.CreateOrderRequest;
import com.moe.orderservice.model.RetrieveOrderDTO;
import com.moe.orderservice.service.OrderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/order")
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UUID placeOrder(@Valid @RequestBody CreateOrderRequest request){
        Optional<RetrieveOrderDTO> retrieveOrderDTO = orderService.placeOrder(request);
        return retrieveOrderDTO.get().orderNumber();

    }

    @GetMapping("{id}")
    public RetrieveOrderDTO getOrderDetails(@PathVariable UUID id){
        RetrieveOrderDTO orderDetails = orderService.getOrderDetails(id);
        return orderDetails;


    }

}
