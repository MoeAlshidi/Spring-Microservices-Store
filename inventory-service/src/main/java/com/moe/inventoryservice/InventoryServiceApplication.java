package com.moe.inventoryservice;

import com.moe.inventoryservice.model.Inventory;
import com.moe.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);

	}
	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository){
		return args -> {
			Inventory inventory = new Inventory();
			inventory.setQuantity(100);
			inventory.setSkuCode("iphone_15iphone_15");


			Inventory inventory1 = new Inventory();
			inventory1.setQuantity(0);
			inventory1.setSkuCode("iphone_15_red");

			inventoryRepository.save(inventory);
			inventoryRepository.save(inventory1);
		};
	}

}
