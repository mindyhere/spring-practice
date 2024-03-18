package com.example.spring03.controller.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ImageUploadController {
	@PostMapping("imageUpload.do")
	public void imageUpload(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(name = "upload") MultipartFile upload) throws Exception {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// 클라이언트 →(저장)→ 서버
		OutputStream out = null;
		PrintWriter printWriter = null;
		String fileName = upload.getOriginalFilename();
		byte[] bytes = upload.getBytes(); // 바이트배열
		ServletContext application = request.getSession().getServletContext();
		String uploadPath = application.getRealPath("/resources/images/"); // 배포 디렉토리
		out = new FileOutputStream(new File(uploadPath + fileName));
		out.write(bytes);
		printWriter = response.getWriter();
		String fileUrl = request.getContextPath() + "/resources/images/" + fileName;
		printWriter.println("{\"filename\" : \"" + fileName + "\",\"uploaded\":1,\"url\":\"" + fileUrl + "\"}");
		printWriter.flush();
	}

}
