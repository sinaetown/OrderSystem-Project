package com.ecommerce.ordersystem.orderitem.repository;

import com.ecommerce.ordersystem.orderitem.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrdering_id(Long ordering_id);
}
