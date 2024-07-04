package com.Inventory.System.notifications.TRA.Model;


import jakarta.persistence.Entity;
import lombok.Data;

//This is for sending notification to the customer
@Data
@Entity
public class Notification extends BaseEntity{

    String subject;
    String message;
    String recipient;
    Status status;// This is an Enum
    Type type; //This is an Enum


}
