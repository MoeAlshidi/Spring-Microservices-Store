package com.moe.inventoryservice.service;

import org.springframework.stereotype.Service;

@Service
public interface IInventoryService {
    Boolean isInStock(String skuCode);
}
