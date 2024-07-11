package com.Inventory.System.notifications.TRA.DTO;

import com.Inventory.System.notifications.TRA.Model.Product;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProductDTO {

    ProductDetailsDTO productDetailsDTO;

    // Converts a Product entity to DTO
    public static ProductDTO convertToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductDetailsDTO(ProductDetailsDTO.convertToDTO(product.getProductDetails()));
        return productDTO;
    }

    // Converts a list of Product entities to DTOs
    public static List<ProductDTO> convertToDTO(List<Product> productList) {
        List<ProductDTO> productDTOList = new ArrayList<>();

        for (Product product : productList) {
            ProductDTO dto = convertToDTO(product);
            productDTOList.add(dto);
        }

        return productDTOList;
    }
}
