package com.example.spring03.controller.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring03.model.message.MessageDTO;
import com.example.spring03.service.message.MessageService;

@RestController
@RequestMapping("/messages/*")
public class MessageController {
	@Autowired
	MessageService service;

	@RequestMapping("/")
	public ResponseEntity<String> addMessage(@RequestBody MessageDTO dto) {
		// @RequestBody: 입력데이터 → json 변환 (cf.@ResponseBody: 리턴데이터 → json)

		ResponseEntity<String> entity = null;
		// 데이터 리턴+상태코드
		
		try {
			service.insertMessage(dto);
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
			//									  데이터		상태코드(200)
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
			//									  에러메세지			상태코드(405)
		}
		return entity;
	}
}
