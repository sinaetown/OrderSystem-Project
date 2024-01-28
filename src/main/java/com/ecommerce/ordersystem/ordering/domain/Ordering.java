package com.ecommerce.ordersystem.ordering.domain;

import com.ecommerce.ordersystem.member.domain.Member;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Ordering {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    요구사항: member (Member와 ManyToOne관계, NotNull)
//    신애: nullable=false인 이유는 : 주문이 생성되었는데 주문자가 없을 리가 없어!
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false) //자동으로 member의 PK와 연결됨
    private Member member;

    @Enumerated(EnumType.STRING)
    @Setter
    private OrderStatus orderStatus;

    @CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdTime;

    @UpdateTimestamp
    @Column(columnDefinition = "TIMESTAMP ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updatedTime;

    public void statusToCancel(){
        this.orderStatus = OrderStatus.CANCELED;
    }
}
