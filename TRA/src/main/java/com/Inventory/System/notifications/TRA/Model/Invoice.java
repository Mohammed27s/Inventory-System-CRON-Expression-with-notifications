package com.Inventory.System.notifications.TRA.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Invoice extends BaseEntity {

    String invoiceNumber;
    Date invoiceDate;
    Date dueDate;
    Double PaidAmount;
    String MoneyCurrency;
    @Enumerated(EnumType.STRING)
    PaymentMethod paymentMethod;
    @OneToMany
    List<Product> products; //This store all the exiting products
    Double totalAmount;


}
