package com.example.jpa03.controller;

import java.io.File;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.jpa03.dto.ProductDTO;
import com.example.jpa03.entity.Product;
import com.example.jpa03.repository.ProductRepository;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/product/*")
public class ProductController {
	@Autowired
	ProductRepository productRepository;
	@Autowired
	ModelMapper modelMapper;

	@GetMapping("write")
	public String write() {
		return "/shop/product_write";
	}

	@PostMapping("insert")
	public String insert(ProductDTO dto, HttpServletRequest request) {
		String filename = "-";
		if (!dto.getFile1().isEmpty()) {
			filename = dto.getFile1().getOriginalFilename();
			try {
				// 배포 디렉토리에 추가하는 방식
				ServletContext application = request.getSession().getServletContext();
				String path = application.getRealPath("/images/");
				System.out.println("path:"+path);
				// 개발디렉토리에 추가하는 방식
				//String path = "C:\\work\\jpa03\\src\\main\\resources\\static\\images\\";
				new File(path).mkdir();
				dto.getFile1().transferTo(new File(path + filename));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		dto.setFilename(filename);
		Product p = modelMapper.map(dto, Product.class);
		productRepository.save(p);
		return "redirect:/product/list";
	}

	@RequestMapping(value = "list", 
			method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView list(@RequestParam(name = "productName", defaultValue = "") String productName,
			ModelAndView mav) {
		mav.setViewName("/shop/product_list");
		if (productName.equals("")) {
			mav.addObject("list", productRepository.findAll(Sort.by(Sort.Direction.ASC, "productName")));
		} else {
			mav.addObject("list",
					productRepository.findByProductNameContaining(productName, Sort.by(Sort.Direction.ASC, "productName")));
		}
		mav.addObject("productName", productName);
		return mav;
	}

	@GetMapping("edit/{productCode}")
	public ModelAndView edit(@PathVariable(name = "productCode") long productCode, ModelAndView mav) {
		mav.setViewName("/shop/product_edit");
		Optional<Product> opt = productRepository.findById(productCode);
		Product dto = opt.get();
		mav.addObject("dto", dto);
		return mav;
	}

	@PostMapping("update")
	public String update(ProductDTO dto, HttpServletRequest request) {
		String filename = "-";
		if (!dto.getFile1().isEmpty()) {
			filename = dto.getFile1().getOriginalFilename();
			try {
				// 배포 디렉토리에 추가하는 방식
				ServletContext application = request.getSession().getServletContext();
				String path = application.getRealPath("/images/");
				// 개발디렉토리에 추가하는 방식
				// String path = "C:\\work\\jpa03\\src\\main\\resources\\static\\images\\";
				new File(path).mkdir();
				dto.getFile1().transferTo(new File(path + filename));
			} catch (Exception e) {
				e.printStackTrace();
			}
			dto.setFilename(filename);
		} else {
			Optional<Product> opt = productRepository.findById(dto.getProductCode());
			Product dto2 = opt.get();
			dto.setFilename(dto2.getFilename());
		}
		Product p = modelMapper.map(dto, Product.class);
		productRepository.save(p);
		return "redirect:/product/list";
	}

	@PostMapping("delete")
	public String delete(@RequestParam(name = "productCode") long productCode, HttpServletRequest request) {
		Optional<Product> opt = productRepository.findById(productCode);
		Product dto = opt.get();
		String filename = dto.getFilename();
		if (filename != null && !filename.equals("-")) {
			ServletContext application = request.getSession().getServletContext();
			String path = application.getRealPath("/static/images/");
			// String path = "c:/work_spring/jpa03/src/main/resources/static/images/";

			File f = new File(path + filename);
			if (f.exists())
				f.delete();
		}
		productRepository.deleteById(productCode);
		return "redirect:/product/list";
	}

	@GetMapping("detail/{productCode}")
	public ModelAndView detail(@PathVariable(name = "productCode") long productCode, ModelAndView mav) {
		mav.setViewName("/shop/product_detail");
		Optional<Product> opt = productRepository.findById(productCode);
		Product dto = opt.get();
		mav.addObject("dto", dto);
		return mav;
	}
}