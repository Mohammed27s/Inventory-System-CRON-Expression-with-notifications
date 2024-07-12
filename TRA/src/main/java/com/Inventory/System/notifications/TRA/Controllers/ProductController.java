package com.Inventory.System.notifications.TRA.Controllers;


import com.Inventory.System.notifications.TRA.DTO.ProductDTO;
import com.Inventory.System.notifications.TRA.Model.Product;
import com.Inventory.System.notifications.TRA.Repository.ProductDetailsRepo;
import com.Inventory.System.notifications.TRA.Repository.ProductRepo;
import com.Inventory.System.notifications.TRA.Services.ProductService;
import com.Inventory.System.notifications.TRA.Services.SlackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepo productRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductDetailsRepo productDetailsRepository;

    // Endpoint to add a new product.
    @PostMapping("add")
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @Autowired
    private SlackService slackService;

    // Endpoint to delete a product by its ID.
    @PostMapping("delete")
    public ResponseEntity<?> delete(@RequestParam Integer id) throws Exception {
        try {
            String result = productService.deleteProduct(id);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Deleting failed! " + e.getMessage());
        }
    }

    // Endpoint to update a product's quantity by its ID.
    @PutMapping("update")
    public ResponseEntity<?> updateProduct(@RequestParam Integer id, @RequestParam Integer quantity) throws Exception {
        try {
            String result = productService.updateProduct(id, quantity);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Updating failed! " + e.getMessage());
        }
    }

    // Endpoint to retrieve all products.
    @GetMapping("getAll")
    public List<ProductDTO> getProducts() {
        return productService.getProducts();
    }

    // Endpoint to retrieve products by name.
    @GetMapping("getByName")
    public ResponseEntity<?> getProductByName(@RequestParam String name) throws Exception {
        try {
            List<Product> result = productService.getProductsByName(name);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Retrieving products by name failed! " + e.getMessage());
        }
    }

    // Endpoint to retrieve products by color.
    @GetMapping("getByColor")
    public ResponseEntity<?> getProductByColor(@RequestParam String color) throws Exception {
        try {
            List<Product> result = productService.getProductsByColor(color);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Retrieving products by color failed! " + e.getMessage());
        }
    }

    // Endpoint to retrieve products by size.
    @GetMapping("getBySize")
    public ResponseEntity<?> getProductBySize(@RequestParam String size) {
        try {
            List<Product> result = productService.getProductsBySize(size);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Retrieving products by size failed! " + e.getMessage());
        }
    }

    // Endpoint to retrieve a product by its ID.
    @GetMapping("getById")
    public ResponseEntity<?> getProductById(@RequestParam Integer id) {
        try {
            Product result = productService.getProductById(id);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Retrieving product by ID failed! " + e.getMessage());
        }
    }

    // Endpoint to retrieve products by price.
    @GetMapping("getByPrice")
    public ResponseEntity<?> getProductByPrice(@RequestParam Double price) {
        try {
            List<Product> result = productService.getProductsByPrice(price);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Retrieving products by price failed! " + e.getMessage());
        }
    }

    // Endpoint to retrieve products by country of origin.
    @GetMapping("getByCountry")
    public ResponseEntity<?> getProductByCountryOfOrigin(@RequestParam String country) {
        try {
            List<Product> result = productService.getProductsByCountryOfOrigin(country);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Retrieving products by country of origin failed! " + e.getMessage());
        }
    }

    // Endpoint to retrieve a product by its SKU.
    @GetMapping("getBySKU")
    public ResponseEntity<?> getProductBySKU(@RequestParam UUID sku) {
        try {
            Product result = productService.getProductsBySKU(sku);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Retrieving product by SKU failed! " + e.getMessage());
        }
    }

    // Endpoint to retrieve products by category.
    @GetMapping("getByCategory")
    public ResponseEntity<?> getProductByCategory(@RequestParam String category) {
        try {
            List<Product> result = productService.getProductsByCategory(category);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Retrieving products by category failed! " + e.getMessage());
        }
    }

    // Endpoint to retrieve products by quantity.
    @GetMapping("getByQuantity")
    public ResponseEntity<?> getProductByQuantity(@RequestParam Integer quantity) {
        try {
            List<Product> result = productService.getProductsByQuantity(quantity);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Retrieving products by quantity failed! " + e.getMessage());
        }
    }

    // Endpoint to retrieve products by active status.
    @GetMapping("getByIsActive")
    public ResponseEntity<?> getProductByIsActive(@RequestParam Boolean isActive) {
        try {
            List<Product> result = productService.getProductsByIsActive(isActive);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Retrieving products by isActive failed! " + e.getMessage());
        }
    }

    // Scheduled task to check and notify about low stock products.
    @Scheduled(cron = "0 0 0/6 * * *") // Runs every 6 hours
    @GetMapping("/checkStock")
    public List<Product> getLowStockReport() {
        List<Product> lowStockProducts = productService.getLowStockProducts();
        if (!lowStockProducts.isEmpty()) {
            StringBuilder messageBuilder = new StringBuilder();
            messageBuilder.append("Low stock alert:\n");
            for (Product product : lowStockProducts) {
                messageBuilder.append("Product ID: ").append(product.getId())
                        .append(", Product: ").append(product.getProductDetails().getName())
                        .append(", Quantity: ").append(product.getQuantity()).append("\n");
            }
            slackService.sendMessage("taiba", messageBuilder.toString());
        }
        return lowStockProducts;
    }

}
