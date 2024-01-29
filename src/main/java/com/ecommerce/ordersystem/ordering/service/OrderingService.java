package com.ecommerce.ordersystem.ordering.service;

import com.ecommerce.ordersystem.item.domain.Item;
import com.ecommerce.ordersystem.item.repository.ItemRepository;
import com.ecommerce.ordersystem.member.domain.Member;
import com.ecommerce.ordersystem.member.repository.MemberRepository;
import com.ecommerce.ordersystem.ordering.domain.OrderStatus;
import com.ecommerce.ordersystem.ordering.domain.Ordering;
import com.ecommerce.ordersystem.ordering.dto.CreateOrderingReqDto;
import com.ecommerce.ordersystem.ordering.repository.OrderingRepository;
import com.ecommerce.ordersystem.orderitem.domain.OrderItem;
import com.ecommerce.ordersystem.orderitem.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderingService {
    private final OrderingRepository orderingRepository;
    private final MemberRepository memberRepository;

    private final ItemRepository itemRepository;
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderingService(OrderingRepository orderingRepository,
                           MemberRepository memberRepository,
                           ItemRepository itemRepository,
                           OrderItemRepository orderItemRepository
    ) {
        this.orderingRepository = orderingRepository;
        this.memberRepository = memberRepository;
        this.itemRepository = itemRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Transactional
    public void createOrder(CreateOrderingReqDto createOrderReqDto) throws EntityNotFoundException, IllegalArgumentException {
//        Ordering 테이블에 주문 한 번에 한 개 추가
        Member member = memberRepository.findById(createOrderReqDto.getMemberId()).orElseThrow(()
                -> new EntityNotFoundException("해당하는 ID의 회원이 없습니다!"));
        Ordering ordering = Ordering.builder()
                .member(member)
                .orderStatus(OrderStatus.ORDERED)
                .build();
        orderingRepository.save(ordering);
        member.getOrderings().add(ordering);
        System.out.println(member.getOrderings());

//        OrderItem 테이블에 들어있는 모든 데이터 한 줄에 한 개씩 추가
        for (CreateOrderingReqDto.OrderingItemDto orderingItemDto : createOrderReqDto.getOrderingItemDtos()) {
            Item item = itemRepository.findById(orderingItemDto.getItemId()).orElseThrow(() -> new EntityNotFoundException("해당하는 ID의 아이템이 없습니다!"));
            int afterPurchase = item.getStockQuantity() - orderingItemDto.getCount();
            if (afterPurchase < 0) {
                throw new IllegalArgumentException("수량 부족!");
            } else {
                OrderItem orderItem = OrderItem.builder()
                        .quantity(orderingItemDto.getCount())
                        .ordering(ordering)
                        .item(item)
                        .build();
                orderItemRepository.save(orderItem); //주문 기록은 해야지
                item.purchased(orderingItemDto.getCount()); //재고량 줄여야지 -> dirty check

            }
        }
    }

    @Transactional //statusToCancel에서 dirty check를 가능하게 함!, .save()하지 않아도 됨
    public void cancelOrder(Long id) {
        Ordering ordering = orderingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당하는 ID의 주문 번호가 없습니다! "));
        ordering.statusToCancel(); //ORDERED->CANCELED로 바꿔야지
//        orderingRepository.save(ordering);  //위의 @transactional가 있기 때문에 하지 않아도 됨 (dirty check)

        List<OrderItem> orderItems = orderItemRepository.findByOrdering_id(id);
        for (OrderItem orderItem : orderItems) {
            // 주문 번호에 해당하는 ID는 없을 일이 없어요! => 따로 예외 처리 안 해줘도 돼
            Item item = itemRepository.findById(orderItem.getItem().getId()).get();
            item.canceled(orderItem.getQuantity()); //재고량 다시 늘려야지
            itemRepository.save(item); //dirty check isn't happening here!
        }
    }
}
