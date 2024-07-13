package com.Inventory.System.notifications.TRA.Services;

import com.Inventory.System.notifications.TRA.DTO.OrderDTO;
import com.Inventory.System.notifications.TRA.Model.*;
import com.Inventory.System.notifications.TRA.Repository.OrderRepo;
import com.Inventory.System.notifications.TRA.Repository.ProductDetailsRepo;
import com.Inventory.System.notifications.TRA.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    ProductDetailsRepo productDetailsRepo;

    @Autowired
    ProductRepo productRepo;

    // Method to create an order
    public Order createOrder(Order order) {
        // Create a new product and product details
        Product product = new Product();
        ProductDetails productDetails = new ProductDetails();
        productDetails.setName("Nike");
        productDetails.setColor("red");
        productDetails.setSize("Big");
        productDetails.setPrice(240d);
        productDetails.setCountryMade("UK");
        productDetails.setDescription("Nike brand");
        productDetails.setCreatedDate(new Date());
        productDetails = productDetailsRepo.save(productDetails);

        // Configure the product
        product.setProductDetails(productDetails);
        product.setSku(UUID.randomUUID());
        product.setCategory("Sneaker");
        product.setQuantity(1);
        product.setIsActive(Boolean.TRUE);
        product.setCreatedDate(new Date());
        product = productRepo.save(product);

        // Configure the order
        order.setProductsOrdered(Arrays.asList(product));
        order.setCategoryName("Sneaker");
        order.setCreatedDate(new Date());
        order.setOrderDate(new Date());
        order.setIsActive(Boolean.TRUE);
        order.setStatus(OrderStatus.IN_PROGRESS);
        order.setPaymentStatus(PaymentStatus.PAID);
        order.setPaymentType(PaymentType.BANK_TRANSFER);
        order.setDueDate(new Date());

        return orderRepo.save(order);
    }

    // Method to cancel an order
    public String cancelOrder(Integer id) {
        Order order = orderRepo.getOrderById(id);
        if (order != null && order.getStatus() == OrderStatus.IN_PROGRESS) {
            order.setStatus(OrderStatus.CANCELED);
            if (order.getPaymentStatus() == PaymentStatus.PAID) {
                order.setPaymentStatus(PaymentStatus.REJECTED);
            }
            orderRepo.save(order);
            return "Order canceled.";
        } else {
            return "Unable to cancel order.";
        }
    }

    // Method to update an order
    public String updateOrder(Integer id) throws Exception {
        Order order = orderRepo.getOrderById(id);
        if (order == null) {
            throw new Exception("Order not found with ID: " + id);
        }
        // Perform update operations if needed
        orderRepo.save(order);
        return "Success";
    }

    // Method to retrieve all orders and convert them to DTOs
    public List<OrderDTO> getOrders() {
        List<Order> orders = orderRepo.findAll();
        return OrderDTO.convertToDTO(orders);
    }

    // Method to retrieve an order by its ID
    public Order getOrdersById(Integer id) throws Exception {
        Order order = orderRepo.getOrderById(id);
        if (order == null) {
            throw new Exception("Order not found with ID: " + id);
        }
        return order;
    }

    // Method to retrieve orders by category name
    public List<Order> getOrdersByCategoryName(String categoryName) throws Exception {
        List<Order> orders = orderRepo.getOrderByCategoryName(categoryName);
        if (orders.isEmpty()) {
            throw new Exception("Order not found with the category name: " + categoryName);
        }
        return orders;
    }

    // Method to retrieve orders by order status
    public List<Order> getOrdersByOrderStatus(OrderStatus status) throws Exception {
        List<Order> orders = orderRepo.getOrderByOrderStatus(status);
        if (orders.isEmpty()) {
            throw new Exception("Order not found with the order status: " + status);
        }
        return orders;
    }

    // Method to retrieve orders by payment status
    public List<Order> getOrdersByPaymentStatus(PaymentStatus paymentStatus) {
        return orderRepo.getOrderByPaymentStatus(paymentStatus);
    }

    // Method to retrieve orders by payment type
    public List<Order> getOrdersByPaymentType(PaymentType paymentType) {
        return orderRepo.getOrderByPaymentType(paymentType);
    }
}
