package com.Inventory.System.notifications.TRA.Services;

import com.TRA.tra24Springboot.DTO.OrderDTO;
import com.TRA.tra24Springboot.Models.*;
import com.TRA.tra24Springboot.Repositories.OrderRepository;
import com.TRA.tra24Springboot.Repositories.ProductDetailsRepository;
import com.TRA.tra24Springboot.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductDetailsRepository productDetailsRepository;

    @Autowired
    ProductRepository productRepository;

    // Method to create an order
    public Order createOrder(Order order) {
        // Create a new product and product details
        Product product = new Product();
        ProductDetails productDetails = new ProductDetails();
        productDetails.setName("Apple");
        productDetails.setColor("Green");
        productDetails.setSize("Small");
        productDetails.setPrice(10d);
        productDetails.setCountryOfOrigin("USA");
        productDetails.setDescription("Apple Product");
        productDetails.setCreatedDate(new Date());
        productDetails = productDetailsRepository.save(productDetails);

        // Configure the product
        product.setProductDetails(productDetails);
        product.setSku(UUID.randomUUID());
        product.setCategory("Electronics");
        product.setQuantity(1);
        product.setIsActive(Boolean.TRUE);
        product.setCreatedDate(new Date());
        product = productRepository.save(product);

        // Configure the order
        order.setProductsOrdered(Arrays.asList(product));
        order.setCategoryName("Electronics");
        order.setCreatedDate(new Date());
        order.setOrderDate(new Date());
        order.setIsActive(Boolean.TRUE);
        order.setStatus(OrderStatus.IN_PROGRESS);
        order.setPaymentStatus(PaymentStatus.PAID);
        order.setPaymentType(PaymentType.BANK_TRANSFER);
        order.setDueDate(new Date());

        return orderRepository.save(order);
    }

    // Method to cancel an order
    public String cancelOrder(Integer id) {
        Order order = orderRepository.getOrderById(id);
        if (order != null && order.getStatus() == OrderStatus.IN_PROGRESS) {
            order.setStatus(OrderStatus.CANCELED);
            if (order.getPaymentStatus() == PaymentStatus.PAID) {
                order.setPaymentStatus(PaymentStatus.REJECTED);
            }
            orderRepository.save(order);
            return "Order canceled.";
        } else {
            return "Unable to cancel order.";
        }
    }

    // Method to update an order
    public String updateOrder(Integer id) throws Exception {
        Order order = orderRepository.getOrderById(id);
        if (order == null) {
            throw new Exception("Order not found with ID: " + id);
        }
        // Perform update operations if needed
        orderRepository.save(order);
        return "Success";
    }

    // Method to retrieve all orders and convert them to DTOs
    public List<OrderDTO> getOrders() {
        List<Order> orders = orderRepository.findAll();
        return OrderDTO.convertToDTO(orders);
    }

    // Method to retrieve an order by its ID
    public Order getOrdersById(Integer id) throws Exception {
        Order order = orderRepository.getOrderById(id);
        if (order == null) {
            throw new Exception("Order not found with ID: " + id);
        }
        return order;
    }

    // Method to retrieve orders by category name
    public List<Order> getOrdersByCategoryName(String categoryName) throws Exception {
        List<Order> orders = orderRepository.getOrderByCategoryName(categoryName);
        if (orders.isEmpty()) {
            throw new Exception("No orders found with the category name: " + categoryName);
        }
        return orders;
    }

    // Method to retrieve orders by order status
    public List<Order> getOrdersByOrderStatus(OrderStatus status) throws Exception {
        List<Order> orders = orderRepository.getOrderByOrderStatus(status);
        if (orders.isEmpty()) {
            throw new Exception("No orders found with the order status: " + status);
        }
        return orders;
    }

    // Method to retrieve orders by payment status
    public List<Order> getOrdersByPaymentStatus(PaymentStatus paymentStatus) {
        return orderRepository.getOrderByPaymentStatus(paymentStatus);
    }

    // Method to retrieve orders by payment type
    public List<Order> getOrdersByPaymentType(PaymentType paymentType) {
        return orderRepository.getOrderByPaymentType(paymentType);
    }
}
