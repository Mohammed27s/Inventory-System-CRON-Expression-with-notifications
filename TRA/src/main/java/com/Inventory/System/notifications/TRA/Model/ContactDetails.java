package com.Inventory.System.notifications.TRA.Model;


import jakarta.persistence.Entity;
import lombok.Data;

//This is store contact Information for all class that has contact info
@Entity
@Data
public class ContactDetails extends BaseEntity{

    String email;
    String phoneNumber;
    String faxNumber;
    String address;
    String postalCode;


}
