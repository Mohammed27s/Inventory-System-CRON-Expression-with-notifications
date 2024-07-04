package com.Inventory.System.notifications.TRA.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Supplier extends BaseEntity{

    String supplierName; //This is company name
    String address;
    @OneToMany
    SupplierDetails supplierDetails;


}
