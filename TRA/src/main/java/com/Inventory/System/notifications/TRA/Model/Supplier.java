package com.Inventory.System.notifications.TRA.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Supplier extends BaseEntity{

    String supplierName; //This is company name
    String address;
    @OneToOne
    SupplierDetails supplierDetails; // This is contains many supplier Details information


    
}
