package com.Inventory.System.notifications.TRA.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @OneToMany
    private List<Product> productsOrdered;  // List of products associated with this order.

    private String categoryName;  // The category name for the order.

    private Date orderDate;  // The date when the order was placed.

    @Enumerated(EnumType.STRING)
    private OrderStatus status;  // The current status of the order.

    private String description;  // Description or notes about the order.

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;  // The payment status of the order.

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;  // The type of payment used for the order.

    private Date dueDate;  // The due date for the order.
}
