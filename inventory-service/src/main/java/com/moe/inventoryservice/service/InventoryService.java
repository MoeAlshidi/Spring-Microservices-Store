package com.moe.inventoryservice.service;

import com.moe.inventoryservice.model.Inventory;
import com.moe.inventoryservice.repository.InventoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class InventoryService implements IInventoryService{
    private final InventoryRepository inventoryRepository;
    @Transactional(readOnly = true)
    @Override
    public Boolean isInStock(String skuCode) {
        Optional<Inventory> bySkuCode = inventoryRepository.findBySkuCode(skuCode);
        return bySkuCode.isPresent();
    }
}
