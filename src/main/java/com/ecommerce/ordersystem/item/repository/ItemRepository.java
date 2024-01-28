package com.ecommerce.ordersystem.item.repository;

import com.ecommerce.ordersystem.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
