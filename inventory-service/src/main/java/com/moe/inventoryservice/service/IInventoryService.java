package com.moe.inventoryservice.service;

import com.moe.inventoryservice.model.InventoryResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IInventoryService {
    List<InventoryResponse> isInStock(List<String> skuCode);
}
