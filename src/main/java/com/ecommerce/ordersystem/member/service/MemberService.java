package com.ecommerce.ordersystem.member.service;

import com.ecommerce.ordersystem.member.domain.Member;
import com.ecommerce.ordersystem.member.domain.Role;
import com.ecommerce.ordersystem.member.dto.CreateMemberReqDto;
import com.ecommerce.ordersystem.member.dto.ShowMemOrderingsResDto;
import com.ecommerce.ordersystem.member.dto.ShowMembersResDto;
import com.ecommerce.ordersystem.member.repository.MemberRepository;
import com.ecommerce.ordersystem.ordering.domain.OrderStatus;
import com.ecommerce.ordersystem.ordering.domain.Ordering;
import com.ecommerce.ordersystem.orderitem.domain.OrderItem;
import com.ecommerce.ordersystem.orderitem.dto.OrderItemResDto;
import com.ecommerce.ordersystem.orderitem.repository.OrderItemRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final OrderItemRepository orderItemRepository;

    public MemberService(MemberRepository memberRepository, OrderItemRepository orderItemRepository) {
        this.memberRepository = memberRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public void createMember(CreateMemberReqDto createMemberReqDto) {
        Role role = Role.USER;
        if (createMemberReqDto.getRole().equals("admin")) role = Role.ADMIN;
        Member member = Member.builder()
                .name(createMemberReqDto.getName())
                .email(createMemberReqDto.getEmail())
                .password(createMemberReqDto.getPassword())
                .address(createMemberReqDto.getAddress())
                .role(role)
                .build();
        memberRepository.save(member);
    }

    public List<ShowMembersResDto> showMembers() {
        List<ShowMembersResDto> showMembersResDtos = new ArrayList<>();
        String role = "user";
        for (Member member : memberRepository.findAll()) {
            if (member.getRole().equals(Role.ADMIN)) role = "admin";
            ShowMembersResDto showMembersResDto = ShowMembersResDto.builder()
                    .id(member.getId())
                    .name(member.getName())
                    .email(member.getEmail())
                    .password(member.getPassword())
                    .address(member.getAddress())
                    .role(role)
//                    .orderings(member.getOrderings()) ?????? needs editing
                    .createdTime(member.getCreatedTime())
                    .build();
            showMembersResDtos.add(showMembersResDto);
        }
        return showMembersResDtos;
    }

    public List<ShowMemOrderingsResDto> showMemOrderings(Long id) {
        List<ShowMemOrderingsResDto> showMemOrderingsResDtos = new ArrayList<>();
        Member member = memberRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("일치하는 ID의 회원이 없습니다!"));
        for (Ordering ordering : member.getOrderings()) {
            Long IdInOrdering = ordering.getId();
            List<OrderItemResDto> orderItemResDtos = new ArrayList<>();
            for (OrderItem o : orderItemRepository.findByOrdering_id(IdInOrdering)) {
                OrderItemResDto oDto = OrderItemResDto.builder()
                        .name(o.getItem().getName())
                        .quantity(o.getQuantity())
                        .build();
                orderItemResDtos.add(oDto);
            }
            String orderStatus = "canceled";
            if (ordering.getOrderStatus().equals(OrderStatus.ORDERED)) orderStatus = "ordered";
//            for (OrderItemResDto oInfo : orderItemResDtos) {
            ShowMemOrderingsResDto showMemOrderingsResDto = ShowMemOrderingsResDto
                    .builder()
                    .orderId(ordering.getId())
                    .orderStatus(orderStatus)
                    .orderItemResDtos(orderItemResDtos)
                    .build();
            showMemOrderingsResDtos.add(showMemOrderingsResDto);
//            }

        }

        return showMemOrderingsResDtos;
    }
}
