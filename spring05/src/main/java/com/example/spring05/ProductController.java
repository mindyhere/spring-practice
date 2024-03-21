package com.example.spring05;

import java.io.File;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ProductController {
	@Autowired
	ProductDAO productDAO;

	@GetMapping("/")
	public String home() {
		return "redirect:/list";
	}

	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(name = "product_name", defaultValue = "") String product_name,
			ModelAndView mav) {
		mav.setViewName("list");
		mav.addObject("list", productDAO.list(product_name));
		mav.addObject("product_name", product_name);
		return mav;
	}

	@GetMapping("/write")
	public String write() {
		return "write";
	}

	@PostMapping("/insert")
	public String insert(@RequestParam Map<String, Object> map, @RequestParam(name = "img") MultipartFile img,
			// Map → key:value 구조(첨부파일 즉, 바이너리 데이터 저장X) 			          첨부파일은 MultipartFile 형태로 별도로 처리
			HttpServletRequest request) {
		String filename = "-";
		if (img != null && !img.isEmpty()) {
			filename = img.getOriginalFilename();
			try {
				ServletContext application = request.getSession().getServletContext();
				String path = application.getRealPath("/resources/images/");
				img.transferTo(new File(path + filename));
			} catch (Exception e) {
				e.printStackTrace();
			}
			map.put("filename", filename);
		} else {
			map.put("filename", "-");
		}
		productDAO.insert(map);
		return "redirect:/list";
	}

	@GetMapping("/detail/{product_code}")
	public ModelAndView detail(@PathVariable(name = "product_code") String product_code, ModelAndView mav) {
		mav.setViewName("detail");
		mav.addObject("product", productDAO.detail(product_code));
		return mav;
	}

	@PostMapping("/update")
	public String update(@RequestParam Map<String, Object> map, @RequestParam(name = "img") MultipartFile img,
			HttpServletRequest request) {
		String filename = "-";
		if (img != null && !img.isEmpty()) {
			filename = img.getOriginalFilename();
			try {
				ServletContext application = request.getSession().getServletContext();
				String path = application.getRealPath("/resources/images/");
				img.transferTo(new File(path + filename));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			String product_code = map.get("product_code").toString();
			Map<String, Object> product = productDAO.detail(product_code);
			filename = product.get("filename").toString();
		}
		map.put("filename", filename);
		productDAO.update(map);
		return "redirect:/list";
	}

	@PostMapping("/delete")
	public String delete(@RequestParam(name = "product_code") int product_code, HttpServletRequest request) {
		String filename = "-";
		
		if (filename != null && !filename.equals("-")) {
			ServletContext application = request.getSession().getServletContext();
			String path = application.getRealPath("/resources/images/");
			File file = new File(path + filename);
			if (file.exists()) {
				file.delete();
			}
		}
		
		productDAO.delete(product_code);
		return "redirect:/list";
	}

}
