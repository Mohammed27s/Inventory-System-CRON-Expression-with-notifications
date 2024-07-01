package com.Inventory.System.notifications.TRA.Model;

//This is the Invoice class, for creating new invoice to the clients

import jakarta.persistence.Entity;
import java.util.UUID;

@Entity
public class InvoiceCreation extends BaseEntity {

    UUID InvoiceNumber; //This is for tracking for the invoice
    String companyName;
    String address;
    String description;
    String taxInfo; // This is information about the tax will be include in the total amount




}
