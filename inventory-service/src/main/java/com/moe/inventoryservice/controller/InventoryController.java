package com.moe.inventoryservice.controller;

import com.moe.inventoryservice.model.InventoryResponse;
import com.moe.inventoryservice.service.InventoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/inventory")
@AllArgsConstructor
@Slf4j
public class InventoryController {
    private final InventoryService inventoryService;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode){
        log.debug("Received inventory check request for skuCode: {}", skuCode);
        return inventoryService.isInStock(skuCode);
    }
}
