package com.Inventory.System.notifications.TRA.Services;

import com.Inventory.System.notifications.TRA.DTO.InventoryDTO;
import com.Inventory.System.notifications.TRA.Model.Inventory;
import com.Inventory.System.notifications.TRA.Model.Product;
import com.Inventory.System.notifications.TRA.Model.ProductDetails;
import com.Inventory.System.notifications.TRA.Repository.InventoryRepo;
import com.Inventory.System.notifications.TRA.Repository.ProductDetailsRepo;
import com.Inventory.System.notifications.TRA.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepo inventoryRepo;

    @Autowired
    private ProductDetailsRepo productDetailsRepo;

    @Autowired
    private ProductRepo productRepo;

    // Method to receive new stock into inventory
    public Inventory saveInventory(Inventory inventory) {
        // Create new product details
        ProductDetails productDetails = new ProductDetails();
        productDetails.setName("Nike");
        productDetails.setColor("Red");
        productDetails.setPrice(240d);
        productDetails.setCountryMade("UK");
        productDetails.setCreatedDate(new Date());

        // Save product details to repository
        productDetails = productDetailsRepo.save(productDetails);

        // Create new product associated with the received inventory
        Product product = new Product();
        product.setProductDetails(productDetails);
        product.setSku(UUID.randomUUID());
        product.setQuantity(1000);
        product.setIsActive(Boolean.TRUE);
        product.setCreatedDate(new Date());

        // Save product to repository
        product = productRepo.save(product);

        // Update inventory with the new product and other details
        inventory.setProducts((List<Product>) Arrays.asList(product));
        inventory.setLocation("London");
        inventory.setManagerName("Richard Hammond");
        inventory.setPhoneNumber("+44 20 7123 4567");
        inventory.setSupplier("DHL");
        inventory.setOpeningHours("7 AM");
        inventory.setClosingHours("6 PM");
        inventory.setCreatedDate(new Date());
        inventory.setIsActive(Boolean.TRUE);

        // Save updated inventory to repository and return
        return inventoryRepo.save(inventory);
    }

    // Method to mark inventory item as written off
    public String writeOff(Integer id) {
        // Fetch inventory item by ID
        Inventory inventory = inventoryRepo.getById(id);

        // Mark inventory item as inactive and update timestamp
        inventory.setIsActive(Boolean.FALSE);
        inventory.setUpdatedDate(new Date());

        // Save updated inventory to repository
        inventoryRepo.save(inventory);

        // Return success message
        return "success";
    }

    // Method to get all inventories and convert to DTOs
    public List<InventoryDTO> getInventory() {
        // Fetch all inventories from repository
        List<Inventory> inventory = inventoryRepo.findAll();

        // Convert and return inventories as DTOs
        return InventoryDTO.convertToDTO(inventory);
    }

    // Method to get inventory item by ID
    public Inventory getInventoriesById(Integer inventoryId) {
        // Fetch inventory item by ID from repository
        return inventoryRepo.getInventoryById(inventoryId);
    }

    // Methods to search inventories by different criteria
    public List<Inventory> getInventoriesByLocation(String inventoryLocation) {
        return inventoryRepo.getInventoryByLocation(inventoryLocation);
    }

    public List<Inventory> getInventoriesByManager(String InventoryManager) {
        return inventoryRepo.getInventoryByManager(InventoryManager);
    }

    public List<Inventory> getInventoriesBySupplier(String InventorySupplier) {
        return inventoryRepo.getInventoryBySupplier(InventorySupplier);
    }

    public List<Inventory> getInventoriesByPhoneNumber(String phoneNumber) {
        return inventoryRepo.getInventoryByPhoneNumber(phoneNumber);
    }

    public List<Inventory> getInventoriesByOpeningHours(String openingHours) {
        return inventoryRepo.getInventoryByOpeningHours(openingHours);
    }

    public List<Inventory> getInventoriesByClosingHours(String closingHours) {
        return inventoryRepo.getInventoryByClosingHours(closingHours);
    }


}

