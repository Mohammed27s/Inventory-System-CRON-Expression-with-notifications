package com.Inventory.System.notifications.TRA.Model;

import jakarta.persistence.Entity;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Product extends BaseEntity{

    Integer quantity;
    String category;
    UUID sku; //This is a unique id for product

}
