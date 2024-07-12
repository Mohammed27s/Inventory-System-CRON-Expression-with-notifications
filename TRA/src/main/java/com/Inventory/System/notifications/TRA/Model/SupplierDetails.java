package com.Inventory.System.notifications.TRA.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.List;

//This is Supplier information
@Entity
@Data
public class SupplierDetails extends BaseEntity{


    String websiteURL; //This is the website link
    String bankDetails; //This is for Information for financial transactions (like bank statement)
    String Notes; //This is a Note for supplier he can add for the customer
    String taxId; // This is for  supplier's tax identification number for financial and regulatory purposes.


}
