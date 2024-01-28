package com.ecommerce.ordersystem.member.dto;

import com.ecommerce.ordersystem.orderitem.dto.OrderItemResDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ShowMemOrderingsResDto {
    private Long orderId;
    private String orderStatus;
    private List<OrderItemResDto> orderItemResDtos;
}
