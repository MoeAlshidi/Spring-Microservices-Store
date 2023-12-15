package com.moe.orderservice.mapper;

import com.moe.orderservice.model.OrderItemDTO;
import com.moe.orderservice.model.OrderLineItems;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrderItemMapper implements Function<OrderLineItems, OrderItemDTO> {


    @Override
    public OrderItemDTO apply(OrderLineItems orderLineItems) {
        return new OrderItemDTO(
                orderLineItems.getId(),
                orderLineItems.getOrderNumber(),
                orderLineItems.getSkuCode(),
                orderLineItems.getPrice(),
                orderLineItems.getQuantity()
        );

    }
}
