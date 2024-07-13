package com.Inventory.System.notifications.TRA.Services;


import com.Inventory.System.notifications.TRA.DTO.ProductDTO;
import com.Inventory.System.notifications.TRA.Model.Product;
import com.Inventory.System.notifications.TRA.Model.ProductDetails;
import com.Inventory.System.notifications.TRA.Repository.ProductDetailsRepo;
import com.Inventory.System.notifications.TRA.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ProductDetailsRepo productDetailsRepo;

    // Method to add a new product
    public Product addProduct(Product product) {
        // Example of creating and saving product details
        ProductDetails productDetails = new ProductDetails();
        productDetails.setName("Nike");
        productDetails.setColor("red");
        productDetails.setSize("Big");
        productDetails.setPrice(240d);
        productDetails.setCountryMade("UK");
        productDetails.setDescription("Nike brand");

        productDetails = productDetailsRepo.save(productDetails);
        product.setProductDetails(productDetails);
        product.setSku(UUID.randomUUID());
        product.setCategory("Sneaker");
        product.setQuantity(1);
        product.setIsActive(Boolean.TRUE);
        product.setCreatedDate(new Date());

        // Save and return the created product
        return productRepo.save(product);
    }

    // Method to delete a product by ID
    public String deleteProduct(Integer id) {
        Product product = productRepo.getById(id);
        product.setIsActive(Boolean.FALSE);
        productRepo.save(product);
        return "Success";
    }

    // Method to update product quantity by ID
    public String updateProduct(Integer id, Integer quantity) {
        Product product = productRepo.getProductById(id);
        product.setQuantity(quantity);
        product.setUpdatedDate(new Date());

        // Save and return success message
        productRepo.save(product);
        return "Updated Successfully";
    }

    // Method to fetch all products and convert to DTOs
    public List<ProductDTO> getProducts() {
        List<Product> products = productRepo.findAll();
        return ProductDTO.convertToDTO(products);
    }

    // Method to fetch products by name
    public List<Product> getProductsByName(String name) throws Exception {
        List<Product> products = productRepo.getProductByName(name);
        if (products.isEmpty()) {
            throw new Exception("Product not found with the name: " + name);
        }
        return products;
    }

    // Methods to fetch products by various criteria (color, size, price, etc.)
    // Similar exception handling for each method
    public List<Product> getProductsByColor(String color) throws Exception {
        List<Product> products = productRepo.getProductByColor(color);
        if (products.isEmpty()) {
            throw new Exception("Product not found with the color: " + color);
        }
        return products;
    }

    public List<Product> getProductsBySize(String size) throws Exception {
        List<Product> products = productRepo.getProductBySize(size);
        if (products.isEmpty()) {
            throw new Exception("Product not found with the size: " + size);
        }
        return products;
    }

    public Product getProductById(Integer id) throws Exception {
        Product product = productRepo.getProductById(id);
        if (product == null) {
            throw new Exception("Product not found with ID: " + id);
        }
        return product;
    }

    public List<Product> getProductsByPrice(Double price) throws Exception {
        List<Product> products = productRepo.getProductByPrice(price);
        if (products.isEmpty()) {
            throw new Exception("Product not found with the price: " + price);
        }
        return products;
    }

    public List<Product> getProductsByCountryOfOrigin(String country) throws Exception {
        List<Product> products = productRepo.getProductByCountryOfOrigin(country);
        if (products.isEmpty()) {
            throw new Exception("Product not found from the country: " + country);
        }
        return products;
    }

    public Product getProductsBySKU(UUID sku) throws Exception {
        Product product = productRepo.getProductBySKU(sku);
        if (product == null) {
            throw new Exception("Product not found with SKU: " + sku);
        }
        return product;
    }

    public List<Product> getProductsByCategory(String category) throws Exception {
        List<Product> products = productRepo.getProductByCategory(category);
        if (products.isEmpty()) {
            throw new Exception("Product not found with the category: " + category);
        }
        return products;
    }

    // Method to fetch products with low stock (quantity less than 50)
    public List<Product> getLowStockProducts() {
        List<Product> products = productRepo.findAll();
        List<Product> lowStockProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getQuantity() < 50) {
                lowStockProducts.add(product);
            }
        }
        return lowStockProducts;
    }

    public List<Product> getProductsByQuantity(Integer quantity) throws Exception {
        List<Product> products = productRepo.getProductByQuantity(quantity);
        if (products.isEmpty()) {
            throw new Exception("Product not found with the quantity: " + quantity);
        }
        return products;
    }

    public List<Product> getProductsByIsActive(Boolean isActive) throws Exception {
        List<Product> products = productRepo.getProductIsActive(isActive);
        if (products.isEmpty()) {
            throw new Exception("Product not found with isActive: " + isActive);
        }
        return products;
    }
}
