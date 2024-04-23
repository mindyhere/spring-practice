package com.example.springmvc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springmvc.model.dto.ProductDTO;

@RestController
public class SampleRestController {
	@ResponseBody
	@RequestMapping("test/doF")
	public ProductDTO doF() {
		return new ProductDTO("냉장고", 5000000);
	}
	
}
