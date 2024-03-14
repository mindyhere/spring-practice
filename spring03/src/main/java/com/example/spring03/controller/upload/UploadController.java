package com.example.spring03.controller.upload;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UploadController {
	String upload_path = "c:/upload/";

	@GetMapping("/upload/input.do")
	public String input() {
		return "upload/input";
	}

	@PostMapping("/upload/upload.do")
	public ModelAndView upload(@RequestParam(name = "file") MultipartFile file, ModelAndView mav) throws Exception {
		String savedName = file.getOriginalFilename(); // 첨부파일의 이름
		UUID uid = UUID.randomUUID();
		savedName = uid.toString() + "_" + savedName;
		File target = new File(upload_path, savedName);
		FileCopyUtils.copy(file.getBytes(), target);
		// 업로드 → 임시디렉토리 → 지정디렉토리
		mav.setViewName("upload/upload_result");
		mav.addObject("saved_name", savedName);
		return mav;
	}
}
