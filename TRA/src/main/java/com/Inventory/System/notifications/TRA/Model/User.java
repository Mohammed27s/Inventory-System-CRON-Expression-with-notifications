package com.Inventory.System.notifications.TRA.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class User extends BaseEntity{

    String name;
    @Enumerated(EnumType.STRING)
    UserTypes userTypes; //This is Enum
    @OneToOne
    ContactDetails contactDetails;
    AccessPrivileges accessPrivileges; //This is an Enum

}
