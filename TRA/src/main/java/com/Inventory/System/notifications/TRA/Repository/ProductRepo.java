package com.Inventory.System.notifications.TRA.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.Inventory.System.notifications.TRA.Model.Product;

import java.util.List;
import java.util.UUID;

// This is the Product Repository for connecting with MySQL
@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

    // Fetches a list of Products by their name.
    @Query("SELECT p FROM Product p WHERE p.productDetails.name = :productName")
    List<Product> getProductByName(@Param("productName") String productName);

    // Fetches a Product by its ID.
    @Query("SELECT p FROM Product p WHERE p.id = :productId")
    Product getProductById(@Param("productId") Integer productId);

    // Fetches a list of Products by their color.
    @Query("SELECT p FROM Product p WHERE p.productDetails.color = :productColor")
    List<Product> getProductByColor(@Param("productColor") String productColor);

    // Fetches a list of Products by their size.
    @Query("SELECT p FROM Product p WHERE p.productDetails.size = :productSize")
    List<Product> getProductBySize(@Param("productSize") String productSize);

    // Fetches a list of Products by their price.
    @Query("SELECT p FROM Product p WHERE p.productDetails.price = :productPrice")
    List<Product> getProductByPrice(@Param("productPrice") Double productPrice);

    // Fetches a list of Products by their country of origin.
    @Query("SELECT p FROM Product p WHERE p.productDetails.countryOfOrigin = :productCountry")
    List<Product> getProductByCountryOfOrigin(@Param("productCountry") String productCountry);

    // Fetches a Product by its SKU.
    @Query("SELECT p FROM Product p WHERE p.sku = :productSKU")
    Product getProductBySKU(@Param("productSKU") UUID productSKU);

    // Fetches a list of Products by their category.
    @Query("SELECT p FROM Product p WHERE p.category = :productCategory")
    List<Product> getProductByCategory(@Param("productCategory") String productCategory);

    // Fetches a list of Products by their quantity.
    @Query("SELECT p FROM Product p WHERE p.quantity = :productQuantity")
    List<Product> getProductByQuantity(@Param("productQuantity") Integer productQuantity);

    // Fetches a list of active Products.
    @Query("SELECT p FROM Product p WHERE p.isActive = :productIsActive")
    List<Product> getProductIsActive(@Param("productIsActive") Boolean productIsActive);
}
