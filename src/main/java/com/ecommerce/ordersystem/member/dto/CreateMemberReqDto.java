package com.ecommerce.ordersystem.member.dto;

import com.ecommerce.ordersystem.member.domain.Role;
import lombok.Data;

@Data
public class CreateMemberReqDto {
    private String name;
    private String email;
    private String password;
    private String address;
    private String role;
}
