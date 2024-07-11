package com.Inventory.System.notifications.TRA.DTO;

import com.Inventory.System.notifications.TRA.Model.Inventory;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class InventoryDTO {
    Integer inventoryId;
    List<ProductDTO> products;
    String location;

    // Converts an Inventory entity to DTO
    public static InventoryDTO convertToDTO(Inventory inventory) {
        InventoryDTO inventoryDTO = new InventoryDTO();
        inventoryDTO.setInventoryId(inventory.getId());
        inventoryDTO.setLocation(inventory.getLocation());

        // Convert list of products to list of ProductDTOs
        if (inventory.getProducts() != null) {
            inventoryDTO.setProducts((List<ProductDTO>) ProductDTO.convertToDTO(inventory.getProducts()));
        }

        return inventoryDTO;
    }

    // Converts a list of Inventory entities to DTOs
    public static List<InventoryDTO> convertToDTO(List<Inventory> inventoryList) {
        List<InventoryDTO> inventoryDTOList = new ArrayList<>();

        for (Inventory inventory : inventoryList) {
            InventoryDTO dto = convertToDTO(inventory);
            inventoryDTOList.add(dto);
        }

        return inventoryDTOList;
    }
}
