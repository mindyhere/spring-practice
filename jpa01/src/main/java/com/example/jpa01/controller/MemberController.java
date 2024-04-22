package com.example.jpa01.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.jpa01.dto.MemberDTO;
import com.example.jpa01.entity.Member;
import com.example.jpa01.repository.MemberRepository;

@Controller
public class MemberController {
	@Autowired
	MemberRepository memberRepository;

	@Autowired
	ModelMapper modelMapper;

	@GetMapping("/")
	public String memberList(Model model) {
		List<Member> list = memberRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
		model.addAttribute("list", list);
		return "member/list";
	}

	@GetMapping("write")
	public String write() {
		return "member/write";
	}

	@PostMapping("insert")
	public String insert(@ModelAttribute MemberDTO dto) {
		Member member = modelMapper.map(dto, Member.class); // dto → entity매핑
		member.setJoin_date(new Date());
		memberRepository.save(member); // insert query 실행, 레코드 저장
		return "redirect:/";
	}

	@GetMapping("view")
	public String view(@RequestParam(name = "userid") String userid, Model model) {
		Optional<Member> result = memberRepository.findById(userid);
		if (result.isPresent()) {
			Member member = result.get();
			model.addAttribute("dto", member);
			return "member/detail";
		} else {
			return "/";
		}
	}

	@PostMapping("update")
	public String update(@ModelAttribute MemberDTO dto, Model model) {
		Optional<Member> result = memberRepository.findByUseridAndPasswd(dto.getUserid(), dto.getPasswd());
		// findBy필드And필드

		if (result.isPresent()) {
			// 레코드가 존재하면
			Member member = modelMapper.map(dto, Member.class); // dto → entity매핑
			member.setJoin_date(result.get().getJoin_date());
			memberRepository.save(member); // update
			return "redirect:/";
		} else {
			// 레코드가 없으면
			model.addAttribute("message", "비밀번호가 일치하지 않습니다.");
			Optional<Member> m = memberRepository.findById(dto.getUserid());
			Member member = m.get();
			model.addAttribute("dto", member);
			return "member/detail";
		}
	}

	@PostMapping("delete")
	public String delete(@RequestParam(name = "userid") String userid, @RequestParam(name = "passwd") String passwd,
			Model model) {
		Optional<Member> result = memberRepository.findByUseridAndPasswd(userid, passwd);
		if (result.isPresent()) {
			memberRepository.deleteById(userid); // 레코드 삭제
			return "redirect:/";
		} else {
			model.addAttribute("message", "비밀번호가 일치하지 않습니다.");
			Optional<Member> m = memberRepository.findById(userid);
			Member member = m.get();
			model.addAttribute("dto", member);
			return "member/detail";
		}
	}

}
