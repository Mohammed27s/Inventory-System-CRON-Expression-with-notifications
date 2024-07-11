package com.Inventory.System.notifications.TRA.Model;


import jakarta.persistence.Entity;
import lombok.Data;
import java.util.Date;

@Entity
@Data
public class ProductDetails extends BaseEntity{


    String name;
    String brandName;
    Date expiredate;
    String size;
    String color;
    String description; //This is explaining what the product for
    Double price;


}
