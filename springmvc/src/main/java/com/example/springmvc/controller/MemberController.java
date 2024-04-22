package com.example.springmvc.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.springmvc.model.dto.MemberDTO;
import com.example.springmvc.service.MemberService;

@Controller
public class MemberController {
	@Inject
	MemberService memberService;

	@RequestMapping("member/list.do")
	public String memberList(Model model) {
		System.out.println("memberService : " + memberService);
		List<MemberDTO> list = memberService.memberList();
		model.addAttribute("list", list);
		return "member/member_list";
	}
}
