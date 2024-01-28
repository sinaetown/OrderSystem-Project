package com.ecommerce.ordersystem.orderitem.service;

import com.ecommerce.ordersystem.orderitem.domain.OrderItem;
import com.ecommerce.ordersystem.orderitem.dto.OrderItemResDto;
import com.ecommerce.ordersystem.orderitem.repository.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public List<OrderItemResDto> showOrderItems(Long id) {
        List<OrderItemResDto> orderItemResDtos = new ArrayList<>();
        for (OrderItem orderItem : orderItemRepository.findByOrdering_id(id)) {
            OrderItemResDto orderItemResDto = OrderItemResDto.builder()
                    .quantity(orderItem.getQuantity())
                    .name(orderItem.getItem().getName())
                    .build();
            orderItemResDtos.add(orderItemResDto);
        }
        return orderItemResDtos;
    }

    
}
