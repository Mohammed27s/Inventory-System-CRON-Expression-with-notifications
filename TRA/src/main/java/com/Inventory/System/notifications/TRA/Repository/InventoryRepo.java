package com.Inventory.System.notifications.TRA.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.Inventory.System.notifications.TRA.Model.Inventory;

import java.util.List;

// This is the Inventory Repository for connecting with MySQL
@Repository
public interface InventoryRepo extends JpaRepository<Inventory, Integer> {

    // Fetches an Inventory by its ID.
    @Query("SELECT i FROM Inventory i WHERE i.id = :inventoryId")
    Inventory getInventoryById(@Param("inventoryId") Integer inventoryId);

    // Fetches a list of Inventory items by their location.
    @Query("SELECT i FROM Inventory i WHERE i.location = :inventoryLocation")
    List<Inventory> getInventoryByLocation(@Param("inventoryLocation") String inventoryLocation);

    // Fetches a list of Inventory items managed by a specific manager.
    @Query("SELECT i FROM Inventory i WHERE i.manager = :inventoryManager")
    List<Inventory> getInventoryByManager(@Param("inventoryManager") String inventoryManager);

    // Fetches a list of Inventory items supplied by a specific supplier.
    @Query("SELECT i FROM Inventory i WHERE i.supplier = :inventorySupplier")
    List<Inventory> getInventoryBySupplier(@Param("inventorySupplier") String inventorySupplier);

    // Fetches a list of Inventory items by their phone number.
    @Query("SELECT i FROM Inventory i WHERE i.phoneNumber = :phoneNumber")
    List<Inventory> getInventoryByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    // Fetches a list of Inventory items by their opening hours.
    @Query("SELECT i FROM Inventory i WHERE i.openingHours = :openingHours")
    List<Inventory> getInventoryByOpeningHours(@Param("openingHours") String openingHours);

    // Fetches a list of Inventory items by their closing hours.
    @Query("SELECT i FROM Inventory i WHERE i.closingHours = :closingHours")
    List<Inventory> getInventoryByClosingHours(@Param("closingHours") String closingHours);
}
