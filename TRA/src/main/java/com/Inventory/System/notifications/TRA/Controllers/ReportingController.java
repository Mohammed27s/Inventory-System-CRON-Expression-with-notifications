package com.Inventory.System.notifications.TRA.Controllers;

import com.Inventory.System.notifications.TRA.Model.Inventory;
import com.Inventory.System.notifications.TRA.Model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/report")
public class ReportingController {

    private Inventory globalInventoryItem;

    // Endpoint to generate an inventory report
    @GetMapping("report")
    public String reportInventory() {
        StringBuilder report = new StringBuilder("The Report section \n");

        // Sort products by name
        List<Product> sortedProducts = globalInventoryItem.getProducts().stream()
                .sorted((p1, p2) -> p1.getProductDetails().getName().compareTo(p2.getProductDetails().getName()))
                .collect(Collectors.toList());

        // Append sorted product information to the report
        report.append("Sorted Products:\n").append(parseProductsInfo(sortedProducts));

        // Append inventory details to the report
        report.append("Location: ").append(globalInventoryItem.getLocation()).append("\n")
                .append("Manager: ").append(globalInventoryItem.getManagerName()).append("\n")
                .append("Supplier: ").append(globalInventoryItem.getSupplier()).append("\n")
                .append("Phone Number: ").append(globalInventoryItem.getPhoneNumber()).append("\n")
                .append("Opening Hours: ").append(globalInventoryItem.getOpeningHours()).append("\n")
                .append("Closing Hours: ").append(globalInventoryItem.getClosingHours()).append("\n");

        return report.toString();
    }

    // Helper method to parse and format product information
    private String parseProductsInfo(List<Product> products) {
        StringBuilder productsInfo = new StringBuilder();
        for (Product product : products) {
            productsInfo.append("Name: ").append(product.getProductDetails().getName()).append("\n")
                    .append("Country made: ").append(product.getProductDetails().getCountryMade()).append("\n")
                    .append("Description: ").append(product.getProductDetails().getDescription()).append("\n")
                    .append("Quantity: ").append(product.getQuantity()).append("\n")
                    .append("Category: ").append(product.getCategory()).append("\n")
                    .append("Color: ").append(product.getProductDetails().getColor()).append("\n")
                    .append("Size: ").append(product.getProductDetails().getSize()).append("\n")
                    .append("SKU: ").append(product.getSku()).append("\n")
                    .append("Price: ").append(product.getProductDetails().getPrice()).append("\n")
                    .append("\n"); // Add extra line break between products
        }
        return productsInfo.toString();
    }
}
