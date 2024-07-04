package com.Inventory.System.notifications.TRA.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.UUID;


//This is class to display information for one product information.
@Data
@Entity
public class Product extends BaseEntity{

    Integer quantity;
    String category;
    UUID sku; //This is a unique id for product
    @OneToOne
    ProductDetails productDetails; //This for each product it will has it own description

}
