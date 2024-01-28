package com.ecommerce.ordersystem.member.dto;

import com.ecommerce.ordersystem.member.domain.Role;
import com.ecommerce.ordersystem.ordering.domain.Ordering;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ShowMembersResDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String address;
    private String role;
    private List<Ordering> orderings;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
