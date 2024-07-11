package com.Inventory.System.notifications.TRA.DTO;


import com.Inventory.System.notifications.TRA.Model.Order;
import com.Inventory.System.notifications.TRA.Model.OrderStatus;
import com.Inventory.System.notifications.TRA.Model.PaymentStatus;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderDTO {

    Integer orderId;
    OrderStatus status;
    PaymentStatus paymentStatus;
    List<ProductDTO> productsOrdered;

    // Converts an Order entity to DTO
    public static OrderDTO convertToDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(order.getId());
        orderDTO.setPaymentStatus(order.getPaymentStatus());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setProductsOrdered(ProductDTO.convertToDTO(order.getProductsOrdered()));

        return orderDTO;
    }

    // Converts a list of Order entities to DTOs
    public static List<OrderDTO> convertToDTO(List<Order> orderList) {
        List<OrderDTO> orderDTOList = new ArrayList<>();

        for (Order order : orderList) {
            OrderDTO dto = convertToDTO(order);
            orderDTOList.add(dto);
        }

        return orderDTOList;
    }
}
