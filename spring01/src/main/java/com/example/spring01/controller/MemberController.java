package com.example.spring01.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.spring01.model.MemberDAO;
import com.example.spring01.model.MemberDTO;

@Controller
public class MemberController {
	@Autowired
	MemberDAO memberDao;

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	//System.out.println 대체

	@GetMapping("member/list.do")
	public String memberList(Model model) {
		List<MemberDTO> list = memberDao.list();
		model.addAttribute("list", list);

		//logger.info("회원리스트: " + list);

		return "member/list";
	}

	@GetMapping("member/write.do")
	public String write() {
		return "member/write";
	}

	@PostMapping("member/insert.do")
	public String insert(@ModelAttribute MemberDTO dto) {
		memberDao.insert(dto);
		return "redirect:/member/list.do";
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
	public String delete(@RequestParam(name = "userid") String userid, @RequestParam(name = "passwd") String passwd,
			Model model) {
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
