package com.moe.orderservice.service;

import com.moe.orderservice.exception.ResourceNotFoundException;
import com.moe.orderservice.mapper.OrderMapper;
import com.moe.orderservice.model.*;
import com.moe.orderservice.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class OrderService implements IOrderService{

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final WebClient.Builder webClientBuilder;
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

            //Get List<SkuCodes>
            List<String> skuCodes = collect.stream().map(code -> code.getSkuCode()).toList();
            //Create Order instance
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID());
            order.setOrderLineItemsList(collect);

            // Check if items in stock using Inventory Service
            InventoryResponse[] results = webClientBuilder.build().get()
                    .uri("http://inventory-service/api/v1/inventory",
                            uriBuilder -> uriBuilder.queryParam("skuCode",skuCodes).build())
                    .retrieve()
                    .bodyToMono(InventoryResponse[].class)
                    .block();

            boolean allProductsInStock = Arrays.stream(results).allMatch(InventoryResponse::isInStock);

            if(allProductsInStock){
                orderRepository.save(order);
            }else{
                throw  new IllegalArgumentException("Product is not in stock, Please select another product ");
            }

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
