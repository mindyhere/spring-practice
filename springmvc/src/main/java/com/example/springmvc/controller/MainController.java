package com.example.springmvc.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.springmvc.model.dto.ProductDTO;

@Controller
public class MainController {

	@RequestMapping("/") // 시작url: http://localhost/springmvc/
	public String main(Model model) {
		model.addAttribute("message", "홈페이지 방문을 환영합니다.");
		return "main";
	}

	@GetMapping("gugu.do")
	public String multi_table(Model model) {
		return "test/gugu";
	}

	@PostMapping(value = "gugu_result.do")
	public String gugu(@RequestParam(value = "dan", defaultValue = "3") int dan, Model model) {
		String result = "";
		for (int i = 1; i <= 9; i++) {
			result += dan + "X" + i + " = " + dan * i + "<br>";
		}
		model.addAttribute("result", result);
		return "test/gugu_result";
	}

	@RequestMapping("test")
	public void test() {
	}

	@RequestMapping("test/doA")
	public String doA(Model model) {
//		logger.info("doA called...");
		System.out.println("doA called...");
		model.addAttribute("message", "홈페이지 방문을 환영합니다:)");
		return "test/doB";
	}

	@RequestMapping("test/doB")
	public void doB() {
		System.out.println("doB called...");
	}
	
	@RequestMapping("test/doC")
	public ModelAndView doC() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("product", new ProductDTO("샤프", 1000));
		return new ModelAndView("test/doC", "map", map);
	}
	
	@RequestMapping("test/doD")
	public String doD() {
		System.out.println("doE redirect...");
		return "redirect:/test/doE";
	}
	
	@RequestMapping("test/doE")
	public void doE() {
	}
	
//	@PostMapping("test/doF")
//	public @ResponseBody ProductDTO background() {
//		ProductDTO dto=new ProductDTO("TV", 500000);
//		return dto;
//	}

}
