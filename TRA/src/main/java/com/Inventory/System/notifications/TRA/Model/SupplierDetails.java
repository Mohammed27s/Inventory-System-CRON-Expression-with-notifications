package com.Inventory.System.notifications.TRA.Model;

import jakarta.persistence.Entity;
import lombok.Data;

//This is Supplier information
@Entity
@Data
public class SupplierDetails extends BaseEntity{


    ContactDetails contactDetails; //this is save Contact Details for Supplier
    String website;
    String bankDetails; //This is for Information for financial transactions (like bank statement)
    String Notes;
    String taxId; // This is for  supplier's tax identification number for financial and regulatory purposes.






}
