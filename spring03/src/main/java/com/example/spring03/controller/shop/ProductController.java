package com.example.spring03.controller.shop;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring03.model.shop.ProductDAO;
import com.example.spring03.model.shop.ProductDTO;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/shop/product/*")
public class ProductController {
	@Autowired
	ProductDAO productDao;

	@GetMapping("write.do")
	public String write() {
		return "shop/product_write";
	}

	@PostMapping("insert.do")
	public String insert(ProductDTO dto, HttpServletRequest request) {
		String filename = "-";
		if (!dto.getFile1().isEmpty()) {
			// 첨부파일이 있으면
			filename = dto.getFile1().getOriginalFilename();
			try {
				ServletContext application = request.getSession().getServletContext();
				String path = application.getRealPath("/resources/images/"); // 배포디렉토리에 저장
				new File(path).mkdir(); // 디렉토리 생성
				dto.getFile1().transferTo(new File(path + filename));
				// 임시경로 → 지정경로
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		dto.setFilename(filename);
		productDao.insert(dto);
		return "redirect:/shop/product/list.do";
	}

	@GetMapping("list.do")
	public ModelAndView list(ModelAndView mav) {
		mav.setViewName("/shop/product_list");
		mav.addObject("list", productDao.list());
		return mav;
	}

	@GetMapping("edit/{product_code}")
	public ModelAndView edit(@PathVariable(name = "product_code") int product_code, ModelAndView mav) {
		mav.setViewName("/shop/product_edit");
		mav.addObject("dto", productDao.detail(product_code));
		return mav;
	}

	@PostMapping("update.do")
	public String update(ProductDTO dto, HttpServletRequest request) {
		String filename = "-";
		if (!dto.getFile1().isEmpty()) {
			filename = dto.getFile1().getOriginalFilename();
			try {
				ServletContext application = request.getSession().getServletContext();
				String path = application.getRealPath("/resources/images/");
				new File(path).mkdir();
				dto.getFile1().transferTo(new File(path + filename));
			} catch (Exception e) {
				e.printStackTrace();
			}
			dto.setFilename(filename);
		} else {
			ProductDTO dto2 = productDao.detail(dto.getProduct_code());
			dto.setFilename(dto2.getFilename());
		}
		productDao.update(dto);
		return "redirect:/shop/product/list.do";
	}

	@PostMapping("delete.do")
	public String delete(@RequestParam(name = "product_code") int product_code, HttpServletRequest request) {
		String filename = productDao.file_info(product_code);

		if (filename != null && filename.equals("-")) {
			ServletContext application = request.getSession().getServletContext();
			String path = application.getRealPath("/resources/images/");
			File f = new File(path + filename);
			if (f.exists()) {
				f.delete(); // 첨부파일 삭제
			}
		}
		productDao.delete(product_code);
		return "redirect:/shop/product/list.do";
	}

	@GetMapping("detail/{product_code}")
	public ModelAndView detail(@PathVariable(name = "product_code") int product_code, ModelAndView mav) {
		mav.setViewName("shop/product_detail");
		mav.addObject("dto", productDao.detail(product_code));
		return mav;
	}

}