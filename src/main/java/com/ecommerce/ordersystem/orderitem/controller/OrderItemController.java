package com.ecommerce.ordersystem.orderitem.controller;

import com.ecommerce.ordersystem.orderitem.domain.OrderItem;
import com.ecommerce.ordersystem.orderitem.dto.OrderItemResDto;
import com.ecommerce.ordersystem.orderitem.service.OrderItemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderItemController {
    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping("/orderitems/{id}")
    @ResponseBody
    public List<OrderItemResDto> showOrderItems(@PathVariable(value = "id") Long id) {
        List<OrderItemResDto> orderItems = new ArrayList<>();
        for (OrderItemResDto orderItem : orderItemService.showOrderItems(id)) {
            orderItems.add(orderItem);
            System.out.println(orderItem);
        }
        return orderItems;
    }
}
