package com.example.spring03.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring03.model.member.AdminDAO;
import com.example.spring03.model.member.MemberDTO;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/*") // 공통 url→ http://localhost/admin
public class AdminController {
	@Autowired
	AdminDAO adminDao;

	@GetMapping("login.do") // 세부 url
	public String login() {
		return "admin/login";
	}

	@PostMapping("login_check.do")
	public ModelAndView login_check(MemberDTO dto, HttpSession session, ModelAndView mav) {
		String name = adminDao.login(dto);
		if (name != null) {
			// 로그인 성공 시 세션변수 등록
			session.setAttribute("admin_userid", dto.getUserid());
			session.setAttribute("admin_name", name);
			session.setAttribute("userid", dto.getUserid());
			session.setAttribute("name", name);

			mav.setViewName("admin/admin"); // 이동할 페이지 이름
			mav.addObject("message", "success"); // 전달할 데이터
		} else {
			mav.setViewName("admin/login");
			mav.addObject("message", "error");
		}
		return mav;
	}

	@GetMapping("logout.do")
	public String logout(HttpSession session) {
		session.invalidate(); // 세션초기화
		return "redirect:/admin/login.do?message=logout";
	}

}
