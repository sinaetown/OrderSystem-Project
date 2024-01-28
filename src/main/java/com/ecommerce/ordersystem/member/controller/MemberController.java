package com.ecommerce.ordersystem.member.controller;

import com.ecommerce.ordersystem.member.dto.CreateMemberReqDto;
import com.ecommerce.ordersystem.member.dto.ShowMemOrderingsResDto;
import com.ecommerce.ordersystem.member.dto.ShowMembersResDto;
import com.ecommerce.ordersystem.member.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/member/new")
    @ResponseBody
    public String createMember(CreateMemberReqDto createMemberReqDto) {
        memberService.createMember(createMemberReqDto);
        return "Member Created!";
    }

    @GetMapping("/members")
    @ResponseBody
    public List<ShowMembersResDto> showMembers() {
        return memberService.showMembers();
    }

    @GetMapping("/member/{id}/orderings")
    @ResponseBody
    public List<ShowMemOrderingsResDto> showMemOrderings(@PathVariable(value = "id") Long id) {
        return memberService.showMemOrderings(id);
    }

}
