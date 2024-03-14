package com.example.spring02.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.spring02.model.MemberDAO;
import com.example.spring02.model.MemberDTO;

@Controller
public class MemberController {
	@Autowired
	MemberDAO memberDao;

	@GetMapping("/")
	public String main() {
		return "redirect:/member/list.do";
	}

	@GetMapping("member/list.do")
	public String memberList(Model model) {
		List<MemberDTO> list = memberDao.list();
		model.addAttribute("list", list);
		return "member/list";
	}

	@GetMapping("member/write.do")
	public String write() {
		return "member/write"; // 화면 포워드
	}

	@PostMapping("member/insert.do")
	public String insert(@ModelAttribute MemberDTO dto) {
		memberDao.insert(dto);
		return "redirect:/member/list.do"; // 방향전환
	}

	@GetMapping("member/view.do")
	public String view(@RequestParam(name = "userid") String userid, Model model) {
		model.addAttribute("dto", memberDao.detail(userid));
		return "member/detail";
	}

	@PostMapping("member/update.do")
	public String update(@ModelAttribute MemberDTO dto, Model model) {
		boolean result = memberDao.check_passwd(dto.getUserid(), dto.getPasswd());
		if (result) {
			memberDao.update(dto);
			return "redirect:/member/list.do";
		} else {
			MemberDTO dto2 = memberDao.detail(dto.getUserid());
			dto.setJoin_date(dto2.getJoin_date());
			model.addAttribute("dto", dto);
			model.addAttribute("message", "비밀번호가 일치하지 않습니다.");
			return "member/detail";
		}
	}

	@PostMapping("member/delete.do")
	public String postMethodName(@RequestParam(name = "userid") String userid,
			@RequestParam(name = "passwd") String passwd, Model model) {
		boolean result = memberDao.check_passwd(userid, passwd);
		if (result) {
			memberDao.delete(userid);
			return "redirect:/member/list.do";
		} else {
			model.addAttribute("message", "비밀번호가 일치하지 않습니다.");
			model.addAttribute("dto", memberDao.detail(userid));
			return "member/detail";
		}
	}

}
