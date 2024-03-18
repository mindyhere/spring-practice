package com.example.spring03.controller.pdf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring03.service.pdf.PdfService;

@Controller
@RequestMapping("/pdf/*")
public class PdfController {
	@Autowired
	PdfService pdfService;
	
	@GetMapping("list.do")
	public ModelAndView list() {
		String result=pdfService.createPdf();
		return new ModelAndView("pdf/result", "message", result);
		//				페이지 전달(	뷰 name		변수			값)
	}
	
}
