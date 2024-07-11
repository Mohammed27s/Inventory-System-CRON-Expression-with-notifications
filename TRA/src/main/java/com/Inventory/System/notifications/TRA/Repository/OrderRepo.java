package com.Inventory.System.notifications.TRA.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.Inventory.System.notifications.TRA.Model.Order;
import com.Inventory.System.notifications.TRA.Model.OrderStatus;
import com.Inventory.System.notifications.TRA.Model.PaymentStatus;
import com.Inventory.System.notifications.TRA.Model.PaymentType;

import java.util.List;

// This is the Order Repository for connecting with MySQL
@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {

    // Fetches an Order by its ID.
    @Query("SELECT o FROM Order o WHERE o.id = :orderId")
    Order getOrderById(@Param("orderId") Integer orderId);

    // Fetches a list of Orders by their category name.
    @Query("SELECT o FROM Order o WHERE o.categoryName = :categoryName")
    List<Order> getOrderByCategoryName(@Param("categoryName") String categoryName);

    // Fetches a list of Orders by their status.
    @Query("SELECT o FROM Order o WHERE o.status = :orderStatus")
    List<Order> getOrderByOrderStatus(@Param("orderStatus") OrderStatus orderStatus);

    // Fetches a list of Orders by their payment status.
    @Query("SELECT o FROM Order o WHERE o.paymentStatus = :paymentStatus")
    List<Order> getOrderByPaymentStatus(@Param("paymentStatus") PaymentStatus paymentStatus);

    // Fetches a list of Orders by their payment type.
    @Query("SELECT o FROM Order o WHERE o.paymentType = :paymentType")
    List<Order> getOrderByPaymentType(@Param("paymentType") PaymentType paymentType);
}
