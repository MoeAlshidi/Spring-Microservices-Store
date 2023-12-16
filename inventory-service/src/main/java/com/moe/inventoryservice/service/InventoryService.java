package com.moe.inventoryservice.service;

import com.moe.inventoryservice.model.Inventory;
import com.moe.inventoryservice.model.InventoryResponse;
import com.moe.inventoryservice.repository.InventoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InventoryService implements IInventoryService{
    private final InventoryRepository inventoryRepository;
    @Transactional(readOnly = true)
    @Override
    public List<InventoryResponse> isInStock(List<String> skuCode) {
        List<Inventory> bySkuCode = inventoryRepository.findBySkuCodeIn(skuCode);
        return bySkuCode.stream().map(
                inventory -> new InventoryResponse(
                        inventory.getSkuCode(),
                        inventory.getQuantity() >0
                )).toList();

    }
}
