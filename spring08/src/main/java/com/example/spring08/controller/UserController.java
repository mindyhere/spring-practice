package com.example.spring08.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.spring08.model.dao.UserDAO;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	@Autowired
	PasswordEncoder pwdEncoder; // 비밀번호 암호화 객체

	@Autowired
	UserDAO userDao;

	@RequestMapping("/") // 시작페이지
	public String home() {
		return "home";
	}

	@RequestMapping("/user/login.do")
	public String login() {
		return "user/login"; // 로그인 페이지로 이동
	}
//	로그인 → 로그인 체크  →  성공 : SuccessHandler
//		  (security)	실패 : FailureHandler
	
	@RequestMapping("/user/join.do")
	public String join() {
		return "user/join"; // 회원가입 페이지로 이동
	}

	@RequestMapping("/admin/*") // 관리자 메인 페이지
	public String admin_main() {
		return "admin/main";
	}

	@RequestMapping("/user/insert.do") // 회원정보 저장
	public String insert(@RequestParam(name = "userid") String userid, @RequestParam(name = "passwd") String passwd,
			@RequestParam(name = "name") String name, @RequestParam(name = "authority") String authority) {
		Map<String, Object> map = new HashMap<>();
		map.put("userid", userid);
		String pwd = pwdEncoder.encode(passwd);
		map.put("passwd", pwd);
		map.put("name", name);
		map.put("authority", authority);
		userDao.insert(map);
		return "user/login"; // 로그인 페이지로 이동
	}

	// 로그아웃 처리
	@RequestMapping("/user/logout.do")
	public String logout(HttpSession session) {
		session.invalidate(); // 세션 초기화
		return "redirect:/"; // 시작 페이지로 이동
	}

}
