package com.moe.orderservice.mapper;

import com.moe.orderservice.model.Order;
import com.moe.orderservice.model.OrderItemDTO;
import com.moe.orderservice.model.RetrieveOrderDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderMapper implements Function<Order, RetrieveOrderDTO> {
    private OrderItemMapper orderItemMapper;
    @Override
    public RetrieveOrderDTO apply(Order order) {
        List<OrderItemDTO> items = order.getOrderLineItemsList()
                .stream().map(orderItemMapper)
                .collect(Collectors.toList());
        return new RetrieveOrderDTO(
                order.getId(),
                order.getOrderNumber(),
                items

        );
    }
}
