package com.Inventory.System.notifications.TRA.DTO;


import com.Inventory.System.notifications.TRA.Model.Supplier;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SupplierDTO {

    Integer supplierId;
    String companyName;
    List<OrderDTO> orders;

    // Converts a Supplier entity to DTO
    public static SupplierDTO convertToDTO(Supplier supplier) {
        SupplierDTO supplierDTO = new SupplierDTO();
        supplierDTO.setSupplierId(supplier.getId());
        supplierDTO.setCompanyName(supplier.getSupplierName());
        supplierDTO.setOrders(OrderDTO.convertToDTO(supplier.getOrders()));

        return supplierDTO;
    }

    // Converts a list of Supplier entities to DTOs
    public static List<SupplierDTO> convertToDTO(List<Supplier> supplierList) {
        List<SupplierDTO> supplierDTOs = new ArrayList<>();

        for (Supplier supplier : supplierList) {
            SupplierDTO dto = convertToDTO(supplier);
            supplierDTOs.add(dto);
        }

        return supplierDTOs;
    }
}
