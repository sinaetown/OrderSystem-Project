package com.ecommerce.ordersystem.orderitem.domain;

import com.ecommerce.ordersystem.item.domain.Item;
import com.ecommerce.ordersystem.ordering.domain.Ordering;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

//야이템별 주문에 관한 도메인
@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int quantity;

    //    요구사항: item(Item과 ManyToOne, NotNull)
//    신애: Cascade 옵션을 안 건 이유는 ordering과 orderitem의 관계 불명확? 부모/자녀 관계일까??
//    보통 cascade 옵션은 부모 (@OneToMany일 경우)한테 걸리는데
//    여기서 그럼 item에서 orderitem이라는 필드가 있어야하나 .. ?
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false) //자동으로 item의 PK와 연결됨
    private Item item;

    //    요구사항: ordering(Ordering과 ManyToOne, NotNull)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ordering_id", nullable = false) //자동으로 ordering의 PK와 연결됨
    private Ordering ordering;

    @CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdTime;

    @UpdateTimestamp
    @Column(columnDefinition = "TIMESTAMP ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updatedTime;
}
