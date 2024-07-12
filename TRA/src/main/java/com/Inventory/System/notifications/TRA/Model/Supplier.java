package com.Inventory.System.notifications.TRA.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
public class Supplier extends BaseEntity{

    String supplierName; //This is company name
    String address;
    String country;
    @OneToOne
    SupplierDetails supplierDetails; // This is contains many supplier Details information
    @OneToMany
    List<Order> orders;
    @OneToOne
    ContactDetails contactDetails; //this is save Contact Details for Supplier
    @OneToMany
    List<Product> productsOffered;
    Date nextDeliveryTime;

    @OneToMany
    List<Product> expectedProducts;

    String complaints;
    PaymentType paymentMethods;
    String shippingMethods;
    String minimumOrderQuantity;
    
}
