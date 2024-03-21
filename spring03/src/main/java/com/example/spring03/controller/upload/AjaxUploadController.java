package com.example.spring03.controller.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.spring03.service.board.BoardService;
import com.example.spring03.util.UploadFileUtils;

@Controller
public class AjaxUploadController {
	String upload_path = "c:/upload/";

	@Autowired
	BoardService boardService;

	@GetMapping("/upload/ajax_form")
	public String upload_form() {
		return "/upload/ajax_form";
	}

	@ResponseBody // return: 화면X, Data인 경우
	@RequestMapping(value = "/upload/ajax_upload", produces = "text/plain;charset=utf-8")
	public ResponseEntity<String> ajax_upload(@RequestParam(name = "file") MultipartFile file) throws Exception {
		String filename = UploadFileUtils.uploadFile(upload_path, file.getOriginalFilename(), file.getBytes());
		return new ResponseEntity<String>(filename, HttpStatus.OK);
		// 데이터+상태코드
	}

	@ResponseBody
	@GetMapping("/upload/display_file")
	public ResponseEntity<byte[]> display_file(@RequestParam(name = "file_name") String file_name) {
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;

		try {
			HttpHeaders headers = new HttpHeaders();
			in = new FileInputStream(file_name);
			file_name = file_name.substring(file_name.indexOf("_") + 1);
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.add("Content-Disposition", "attachment; filename=\"" + file_name + "\"");
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.OK);
			// 파일데이터(바이트코드)+상태코드
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return entity;
	}

	@ResponseBody
	@PostMapping(value = "/upload/delete_file")
	public ResponseEntity<String> delete_file(@RequestParam(name = "file_name") String file_name) {
		new File(file_name.replace("/", File.separator)).delete();
		boardService.delete_attach(file_name);
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}
}
