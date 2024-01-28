package com.ecommerce.ordersystem.orderitem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class OrderItemResDto {
    private int quantity;
    private String name;
}
