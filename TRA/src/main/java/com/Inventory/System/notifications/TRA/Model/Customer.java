package com.Inventory.System.notifications.TRA.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Data;


@Entity
@Data
public class Customer extends BaseEntity {


    String customerFullName;
    @OneToOne
    ContactDetails contactDetails; //This is has all contact information for the customer
    Integer age;
    String Nationality;
    String customerType; // This is  individual, corporate
    String Country;

}
