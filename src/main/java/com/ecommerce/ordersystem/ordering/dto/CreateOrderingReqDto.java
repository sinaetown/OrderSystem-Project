package com.ecommerce.ordersystem.ordering.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class CreateOrderingReqDto {
    private Long memberId;
    private List<OrderingItemDto> orderingItemDtos;

    @Data
    public static class OrderingItemDto{
        private Long itemId;
        private int count;
    }
}
