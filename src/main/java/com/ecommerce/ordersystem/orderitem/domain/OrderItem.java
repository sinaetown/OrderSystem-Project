package com.ecommerce.ordersystem.orderitem.domain;

import com.sun.istack.NotNull;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

//    item(Item과 ManyToOne, NotNull)


    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;


//    ordering(Ordering과 ManyToOne, NotNull)

}
