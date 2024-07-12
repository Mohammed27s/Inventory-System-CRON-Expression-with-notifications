package com.Inventory.System.notifications.TRA.Controllers;

import com.Inventory.System.notifications.TRA.DTO.OrderDTO;
import com.Inventory.System.notifications.TRA.Model.Order;
import com.Inventory.System.notifications.TRA.Model.OrderStatus;
import com.Inventory.System.notifications.TRA.Model.PaymentStatus;
import com.Inventory.System.notifications.TRA.Model.PaymentType;
import com.Inventory.System.notifications.TRA.Repository.OrderRepo;
import com.Inventory.System.notifications.TRA.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private OrderService orderService;

    // Endpoint to create a new order.
    @PostMapping("create")
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    // Endpoint to cancel an order by its ID.
    @PostMapping("cancel")
    public String cancelOrder(@RequestParam Integer id) {
        return orderService.cancelOrder(id);
    }

    // Endpoint to update an order by its ID.
    @PutMapping("updateOrder")
    public ResponseEntity<?> updateOrder(@RequestParam Integer id) {
        try {
            String result = orderService.updateOrder(id);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Updating order failed! " + e.getMessage());
        }
    }

    // Endpoint to retrieve all orders.
    @GetMapping("getAll")
    public List<OrderDTO> getOrders() {
        return orderService.getOrders();
    }

    // Endpoint to retrieve an order by its ID.
    @GetMapping("getByOrderId")
    public ResponseEntity<?> getOrderById(@RequestParam Integer id) {
        try {
            Order order = orderService.getOrdersById(id);
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Retrieving order failed! " + e.getMessage());
        }
    }

    // Endpoint to retrieve orders by category name.
    @GetMapping("getByCategoryName")
    public ResponseEntity<?> getOrderByCategoryName(@RequestParam String categoryName) {
        try {
            List<Order> orders = orderService.getOrdersByCategoryName(categoryName);
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Retrieving orders by category name failed! " + e.getMessage());
        }
    }

    // Endpoint to retrieve orders by order status.
    @GetMapping("getByOrderStatus")
    public ResponseEntity<?> getOrderByOrderStatus(@RequestParam OrderStatus status) {
        try {
            List<Order> orders = orderService.getOrdersByOrderStatus(status);
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Retrieving orders by order status failed! " + e.getMessage());
        }
    }

    // Endpoint to retrieve orders by payment status.
    @GetMapping("getByPaymentStatus")
    public List<Order> getOrderByPaymentStatus(@RequestParam PaymentStatus status) {
        return orderService.getOrdersByPaymentStatus(status);
    }

    // Endpoint to retrieve orders by payment type.
    @GetMapping("getByPaymentType")
    public List<Order> getOrderByPaymentType(@RequestParam PaymentType type) {
        return orderService.getOrdersByPaymentType(type);
    }

}
