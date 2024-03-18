package com.example.spring03.controller.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.spring03.model.email.EmailDTO;
import com.example.spring03.service.email.EmailService;

@Controller
@RequestMapping("/email/*")
public class EmailController {
	@Autowired
	EmailService emailService;

	@GetMapping("write.do")
	public String write() {
		return "email/write";
	}

	@PostMapping("send.do")
	public String send(@ModelAttribute EmailDTO dto, Model model) {
		try {
			emailService.sendMail(dto);
			model.addAttribute("message", "이메일이 발송되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "발송 실패되었습니다.");
		}
		return "email/write";
	}

}
