package com.ecommerce.ordersystem.ordering.domain;

import com.sun.istack.NotNull;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class Ordering {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    member(Member와 ManyToOne관계, NotNull)

    private OrderStatus orderStatus;

    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

}
