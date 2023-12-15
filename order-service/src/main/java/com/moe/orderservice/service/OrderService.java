package com.moe.orderservice.service;

import com.moe.orderservice.exception.ResourceNotFoundException;
import com.moe.orderservice.mapper.OrderMapper;
import com.moe.orderservice.model.CreateOrderRequest;
import com.moe.orderservice.model.Order;
import com.moe.orderservice.model.OrderLineItems;
import com.moe.orderservice.model.RetrieveOrderDTO;
import com.moe.orderservice.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class OrderService implements IOrderService{

    private OrderRepository orderRepository;
    private OrderMapper orderMapper;
    @Override
    public Optional<RetrieveOrderDTO> placeOrder(CreateOrderRequest request) {
        if(!request.itemsList().isEmpty()){
            List<OrderLineItems> collect = request.itemsList().stream().map(
                    e ->  OrderLineItems.builder()
                            .orderNumber(e.orderNumber())
                            .skuCode(e.skuCode())
                            .price(e.price())
                            .quantity(e.quantity())
                            .build()
            ).collect(Collectors.toList());
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID());
            order.setOrderLineItemsList(collect);
            orderRepository.save(order);
            return Optional.ofNullable(orderMapper.apply(order));
        }
        return null;
    }

    @Override
    public RetrieveOrderDTO getOrderDetails(UUID id) {
        Order order = orderRepository.findByOrderNumber(id).orElseThrow(ResourceNotFoundException::new);
        return orderMapper.apply(order);
    }
}
