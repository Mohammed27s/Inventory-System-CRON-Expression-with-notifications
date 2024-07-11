package com.Inventory.System.notifications.TRA.Model;


import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Inventory extends BaseEntity {


    ProductDetails productDetails;
    Product products;
    Double unitPrice;
    Integer quantityInStock;
    Integer thresholdForLowStockAlert;
    SupplierDetails supplierDetails;
    String location;

}
