package com.ecommerce.ordersystem.item.service;

import com.ecommerce.ordersystem.item.repository.ItemRepository;
import com.ecommerce.ordersystem.ordering.repository.OrderingRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

}
