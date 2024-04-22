package com.example.jpa02.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.jpa02.dto.MemberDTO;
import com.example.jpa02.entity.Member;
import com.example.jpa02.repository.MemberRepository;

@Controller
public class MemberController {
	@Autowired
	MemberRepository memberRepository; // JpaRepository<테이블클래스, PK자료형> → 기본기능 포함

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
		return "member/write"; // templates/member/write.html
	}

	@PostMapping("insert")
	public String insert(MemberDTO dto) {
		// dto → entity
		Member member = modelMapper.map(dto, Member.class);
		member.setJoin_date(new Date());
		memberRepository.save(member); // insert
		return "redirect:/";
	}

	@GetMapping("view")
	public String view(@RequestParam(name = "userid") String userid, Model model) {
		System.out.println("view... : " + userid);
		Optional<Member> result = memberRepository.findById(userid);
		Member member = result.get();
		model.addAttribute("dto", member);
		return "member/detail";
	}

	@PostMapping("update")
	public String update(MemberDTO dto, Model model) {
		Optional<Member> result = memberRepository.findByUseridAndPasswd(dto.getUserid(), dto.getPasswd());
		if (result.isPresent()) {
			Member member = modelMapper.map(dto, Member.class);
			member.setJoin_date(result.get().getJoin_date());
			memberRepository.save(member); // update
			return "redirect:/";
		} else {
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
