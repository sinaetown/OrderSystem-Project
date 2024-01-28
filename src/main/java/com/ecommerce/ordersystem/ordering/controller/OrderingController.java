package com.ecommerce.ordersystem.ordering.controller;

import com.ecommerce.ordersystem.ordering.dto.CreateOrderingReqDto;
import com.ecommerce.ordersystem.ordering.service.OrderingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OrderingController {
    private final OrderingService orderingService;

    public OrderingController(OrderingService orderingService) {
        this.orderingService = orderingService;
    }

    @PostMapping("/order/new")
    @ResponseBody
    public String createOrder(@RequestBody CreateOrderingReqDto createOrderingReqDto){
        orderingService.createOrder(createOrderingReqDto);
        return "Order created!";
    }

    @PostMapping("/order/{id}/canceled")
    @ResponseBody
    public String cancelOrder(@PathVariable (value="id") Long id){
        orderingService.cancelOrder(id);
        return "Order canceled!";
    }

}
