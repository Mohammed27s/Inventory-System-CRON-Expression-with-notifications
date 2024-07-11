package com.Inventory.System.notifications.TRA.Repository;


import com.Inventory.System.notifications.TRA.Model.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailsRepo extends JpaRepository<ProductDetails, Integer> {





}
