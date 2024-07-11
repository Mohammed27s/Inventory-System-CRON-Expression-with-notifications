package com.Inventory.System.notifications.TRA.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import java.util.UUID;


//This is class to display information for one product information.
@Data
@Entity
@Table(name = "product")
public class Product extends BaseEntity{

    Integer quantity;
    String category;
    UUID sku; //This is a unique id for product
    @OneToOne
    ProductDetails productDetails; //This for each product it will have it own description

}
