package com.ecommerce.ordersystem.item.controller;

import com.ecommerce.ordersystem.item.service.ItemService;
import org.springframework.stereotype.Controller;

@Controller
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }
}
