package com.Inventory.System.notifications.TRA.DTO;

import com.Inventory.System.notifications.TRA.Model.ProductDetails;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductDetailsDTO {
    Integer productId;
    String productName;
    Double productPrice;

    // Converts a ProductDetails entity to DTO
    public static ProductDetailsDTO convertToDTO(ProductDetails productDetails) {
        ProductDetailsDTO productDetailsDTO = new ProductDetailsDTO();
        productDetailsDTO.setProductId(productDetails.getId());
        productDetailsDTO.setProductName(productDetails.getName());
        productDetailsDTO.setProductPrice(productDetails.getPrice());

        return productDetailsDTO;
    }

    // Converts a list of ProductDetails entities to DTOs
    public static List<ProductDetailsDTO> convertToDTO(List<ProductDetails> productDetailsList) {
        List<ProductDetailsDTO> productDetailsDTOList = new ArrayList<>();

        for (ProductDetails productDetails : productDetailsList) {
            ProductDetailsDTO dto = convertToDTO(productDetails);
            productDetailsDTOList.add(dto);
        }

        return productDetailsDTOList;
    }
}
